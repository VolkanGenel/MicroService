package com.volkan.rabbitmq.consumer;

import com.volkan.rabbitmq.model.SaveAuthModel;
import com.volkan.repository.entity.UserProfile;
import com.volkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth")
    public void createUserFromHandleQueue(SaveAuthModel model){
        System.out.println("Gelen Data....: "+model.getUsername());
        userProfileService.saveRabbit(model);
    }
}
