package com.LearnSphere.enrollment_service.listner;

import com.LearnSphere.enrollment_service.config.RabbitMQConfig;
import com.LearnSphere.enrollment_service.dto.UserDeleteEvent;
import com.LearnSphere.enrollment_service.repo.EnrollmentRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HandleUserDelete {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Transactional
    @RabbitListener(queues = RabbitMQConfig.ENROLLMENT_SERVICE_QUEUE)
    public void handleUserDelete(UserDeleteEvent event){
        try {
            System.out.println("🟢 [Listener Triggered] Deleting user with email: " + event.getEmail());
            enrollmentRepo.deleteByStudentEmail(event.getEmail()); // Or enrollmentRepo
            System.out.println("✅ [Deleted] User: " + event.getEmail());
        } catch (Exception ex) {
            System.out.println("❌ [Error] Exception in listener: " + ex.getMessage());
            ex.printStackTrace(); // Most important!
        }
    }
}
