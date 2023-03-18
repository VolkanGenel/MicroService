package com.volkan.service;

import com.volkan.dto.request.DoLoginRequestDto;
import com.volkan.dto.request.RegisterRequestDto;
import com.volkan.dto.request.UserProfileSaveRequestDto;
import com.volkan.exception.AuthServiceException;
import com.volkan.exception.EErrorType;
import com.volkan.manager.IUserProfileManager;
import com.volkan.mapper.IAuthMapper;
import com.volkan.rabbitmq.model.SaveAuthModel;
import com.volkan.rabbitmq.producer.CreateUserProducer;
import com.volkan.repository.IAuthRepository;
import com.volkan.repository.entity.Auth;
import com.volkan.utility.JwtTokenManager;
import com.volkan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    private final CreateUserProducer createUserProducer;
    private final IUserProfileManager iUserProfileManager;

    public AuthService(IAuthRepository repository,
                       JwtTokenManager tokenManager,
                       IUserProfileManager iUserProfileManager,
                       CreateUserProducer createUserProducer) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
        this.iUserProfileManager = iUserProfileManager;
        this.createUserProducer = createUserProducer;
    }

    public Auth register(RegisterRequestDto dto) {
        if(repository.isUsername(dto.getUsername()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        /**
         * Repo -> repository.save(auth); bu bana kaydettiği entityi döner
         * Servi -> save(auth); bu da bana kaydettiği entityi döner
         * direkt -> auth, bir şekilde kayıt edilen entity nin bilgileri istenir ve bunu döner.
         */
//        return repository.save(auth);
        save(auth);
//        iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth));
        createUserProducer.convertAndSend(SaveAuthModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
        return auth;

    }

    public Optional<Auth> findOptionalByUsernameAndPassword(String username, String password) {
        return repository.findOptionalByUsernameAndPassword(username,password);
    }

    /**
     * Kullanıcıyı doğrulayacak ve kullanıcının sistemi içinde hareket edebilmesi için
     * ona özel bir kimlik verecek.
     * Token -> JWT token
     * @param dto
     * @return
     */
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        return tokenManager.createToken(auth.get().getId()).get();
    }
    public List<Auth> findAll (String token) {
        Optional <Long> id= tokenManager.getByIdFromToken(token);
        if(id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if (findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }
}
