package com.learnSphere.auth_service.service;

import com.learnSphere.auth_service.dto.UserDeleteEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserDeleteEvent(String email){
        UserDeleteEvent event = new UserDeleteEvent(email);
        rabbitTemplate.convertAndSend("user.exchange","user.delete",event);
    }
}
