package com.LearnSphere.User_service.listner;

import com.LearnSphere.User_service.dto.UserDeleteEvent;
import com.LearnSphere.User_service.repo.StudentRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class HandleUserDelete {

    @Autowired
    private StudentRepo studentRepo;

    @RabbitListener(queues = "user.delete.queue")
    public void handleUserDelete(UserDeleteEvent event){
        String email = event.getEmail();
        System.out.println("Deleting user with email: "+ email);
        studentRepo.deleteByUserEmail(email);
    }
}
