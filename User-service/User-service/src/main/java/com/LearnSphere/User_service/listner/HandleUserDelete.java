package com.LearnSphere.User_service.listner;

import com.LearnSphere.User_service.config.RabbitMQConfig;
import com.LearnSphere.User_service.dto.UserDeleteEvent;
import com.LearnSphere.User_service.repo.StudentRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HandleUserDelete {

    @Autowired
    private StudentRepo studentRepo;

    @Transactional
    @RabbitListener(queues = RabbitMQConfig.USER_SERVICE_QUEUE)
    public void handleUserDelete(UserDeleteEvent event){
        try {
            System.out.println("🟢 [Listener Triggered] Deleting user with email: " + event.getEmail());
            studentRepo.deleteByUserEmail(event.getEmail()); // Or enrollmentRepo
            System.out.println("✅ [Deleted] User: " + event.getEmail());
        } catch (Exception ex) {
            System.out.println("❌ [Error] Exception in listener: " + ex.getMessage());
            ex.printStackTrace(); // Most important!
        }
    }
}
