package com.LearnSphere.enrollment_service.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String role;
}
