package com.volkan.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
    @Value("${user-service.redis.host}")
    private String redisHost;
    @Value("${user-service.redis.port}")
    private Integer redisPort;
    @Bean
    public LettuceConnectionFactory rediseBaglan() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(redisHost,redisPort)
        );
    }
}
