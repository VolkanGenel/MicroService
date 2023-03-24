package com.volkan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Bean; spring bir nesne yaratırken bean üzerinden bir nesne yaratır.
 * @Configuration -> Konfigürasyon dosyası olarak spring e bildireceğimiz sınıflara ekliyoruz.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ElasticSecurityConfig {

    @Bean
    JwtTokenFilter getTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean //Bu sınıftan bir nesne yaratan method yazar
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * _csrf -> bunu kapatmak için disable komutunu kullanıyoruz.
         */
        httpSecurity.csrf().disable();
        /**
         * Gelen bütün isteklere oturum açılmış mı kendini doğrulamış mı bak.
         * Eğer özel adreslere erişim açmak istiyorsanız bunları belirterek izin vermelisiniz.
         * Match("/{URLS}") için izin ver permitAll demelisiniz.
         */
        httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html").permitAll()
                .anyRequest().authenticated();
        /**
         * Yetkisiz girişlerde kullanıcıların kendilerini doğrulamaları için login formuna yönlendirme yaparız.
         */
        //httpSecurity.formLogin();
        /**
         * Eğer kullanıcıya kendi login formunuzu göstermek istiyor iseniz. O zaman kendi formunuza izin vererek
         * yönlendirme işlemini yapınız.
         */
        //httpSecurity.formLogin().loginPage("/mylogin.html");
        /**
         * Auth servis üzerinden kendisini doğrulayan bir kişinin isteklerinin nasıl işleyeceğini çözmeniz gerekiyor.
         * Kişinin elinde olan token bilgisi okunarak bu kişiye yetkileri dahilinde geçerli olan token üzerinden
         * oturum izni verilecek, böylece işi her seferinde kendini doğrulamak zorunda kalmayacak.
         * Bunu başarmak için öncelikler filter işleminin öncesine bir işlem adımı sokarak kişiyi doğruluyoruz.
         */
        httpSecurity.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
