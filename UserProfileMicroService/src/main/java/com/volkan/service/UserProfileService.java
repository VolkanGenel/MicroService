package com.volkan.service;

import com.volkan.dto.request.UserProfileSaveRequestDto;
import com.volkan.manager.IElasticServiceManager;
import com.volkan.mapper.IUserProfileMapper;
import com.volkan.rabbitmq.model.SaveAuthModel;
import com.volkan.repository.IUserProfileRepository;
import com.volkan.repository.entity.UserProfile;
import com.volkan.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    private final IElasticServiceManager elasticServiceManager;

    public UserProfileService(IUserProfileRepository repository, IElasticServiceManager elasticServiceManager) {
        super(repository);
        this.repository = repository;
        this.elasticServiceManager = elasticServiceManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }

    public void saveRabbit(SaveAuthModel model) {
        UserProfile profile = IUserProfileMapper.INSTANCE.toUserProfile(model);
        save(profile);
        //elasticServiceManager.addUser(profile);
    }

    /**
     * Bu uzun süren bir işlemi simüle etmek için Thread kullanılarak
     * bekletilmiş bir medhodtur.
     * - Bu method, Kağıt kelimesi için her seferinde aynı sonucu mu üretir?
     * -> muhammet => MUHAMMET
     *
     * @param name
     * @return
     */
    @Cacheable(value = "getUpperName")
    public String getUpper(String name) {
        try{
            Thread.sleep(3000L);
        } catch (Exception e) {

        }
        return name.toUpperCase();
    }
    @CacheEvict(value = "getUpperName", allEntries = true)
    public void clearCache() {
        System.out.println("Tüm Kayıtları temizledik");
    }
}
