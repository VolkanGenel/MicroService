package com.volkan.utility;

import com.volkan.manager.IElasticServiceManager;
import com.volkan.repository.entity.UserProfile;
import com.volkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestAndRun {
    private final UserProfileService userProfileService;
    private final IElasticServiceManager elasticServiceManager;
    @PostConstruct
    public void init() {
        /**
         * Bu kısım kullanılacal ise, zorulu durumlar için işiniz bitince
         * bu kodu yorum satırına almak doğru olacaktır.
         * çalışması sistemi etkikemeyen durumlarda thread içinde çalıştırmak doğru olacaktır.
         */
//        new Thread(()->{
//            run();
//        });

    }

    public void run() {
        List<UserProfile> list = userProfileService.findAll();
        list.forEach(x->{
            elasticServiceManager.addUser(x);
        });
    }
}
