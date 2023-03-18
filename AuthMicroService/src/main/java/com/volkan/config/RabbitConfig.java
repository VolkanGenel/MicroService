package com.volkan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private String directExchangeAuth = "direct-exchange-auth";
    private String queueAuth = "queue-auth";
    private String saveBindingKey = "save-auth-key";
    @Bean
    DirectExchange directExchangeAuth() {
        return new DirectExchange(directExchangeAuth);
    }
    @Bean
    Queue queueAuth() {
        return new Queue(queueAuth);
    }
    @Bean
    public Binding bindingSaveDirectExchange(final Queue queueAuth, final DirectExchange directExchangeAuth) {
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveBindingKey);
    }
}
