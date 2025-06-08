package com.LearnSphere.enrollment_service.listner;

import com.LearnSphere.enrollment_service.dto.UserDeleteEvent;
import com.LearnSphere.enrollment_service.repo.EnrollmentRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class HandleUserDelete {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @RabbitListener(queues = "user.delete.queue")
    public void handleUserDelete(UserDeleteEvent event){
        String email = event.getEmail();
        System.out.println("Deleting user with email: "+ email);
        enrollmentRepo.deleteByStudentEmail(email);
    }
}
