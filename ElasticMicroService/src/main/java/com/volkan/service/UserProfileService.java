package com.volkan.service;

import com.volkan.dto.request.AddUserRequestDto;
import com.volkan.dto.request.BaseRequestDto;
import com.volkan.mapper.IUserProfileMapper;
import com.volkan.repository.IUserProfileRepository;
import com.volkan.repository.entity.UserProfile;
import com.volkan.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Optional<UserProfile> findByAuthId(Long authid) {
       return repository.findOptionalByAuthid(authid);
    }

    public void saveDto(AddUserRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
            if(!repository.existsByUserprofileid(dto.getId()))
                save(IUserProfileMapper.INSTANCE.toProfile(dto));
    }
    /**
     * Sayfalama işlemlerinde belli bilgiler olmadan işlem yapmak mümkün olmayacaktır.
     * -> Liste gelmeli
     * -> Total kayıt miktarı
     * -> Hangi sayfadayım
     * -> Kaç sayfa var?
     * -> sonraki sayfa var mı?
     * -> son sayfada mıyım?
     */
    public Page<UserProfile> findAll(BaseRequestDto dto) {
        /**
         * Sıralama ve Sayfalama için bize nesneler ve ayarlamalar gerekli.
         */
        Pageable pageable = null;
        Sort sort = null;
        /**
         * Eğer kişi sıralama istediği alanı yazmamış ise sıralama yapmak istemiyordur.
         */
        if(dto.getSortParameter()!=null) {
            String direction = dto.getDirection()==null ? "ASC" : dto.getDirection();
            sort = Sort.by(Sort.Direction.fromString(direction), dto.getSortParameter());
        }
        /**
         * 1. durum -> sıralama yapmak ister ve sayfalama yapmak ister
         * 2. durum -> sıralama istemiyor ve sayfalama yapmak istiyor.
         * 3. durum -> sıralama istemiyor ve sayfalama yapmak istemiyor.
         */
        Integer pageSize = dto.getPageSize() == null ? 10 :
                dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        if(sort!=null && dto.getCurrentPage()!=null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize, sort);
        } else if (sort==null && dto.getCurrentPage()!=null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize);
        } else {
          pageable = PageRequest.of(0,pageSize);
        }
        return repository.findAll(pageable);
    }
}
