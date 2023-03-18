package com.volkan.rabbitmq.producer;

import com.volkan.rabbitmq.model.SaveAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    /**
     * Belli bir bilginin RabbitMQ üzerinde iletilmesi işlemi
     */
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(SaveAuthModel model) {
        rabbitTemplate.convertAndSend("direct-exchange-auth","save-auth-key",model);
    }
}
