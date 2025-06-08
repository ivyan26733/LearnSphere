package com.LearnSphere.User_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EnrollServiceDTO {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String role;
}
