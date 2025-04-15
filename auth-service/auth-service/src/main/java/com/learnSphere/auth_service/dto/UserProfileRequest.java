package com.learnSphere.auth_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRequest {
    private String userName;
    private String userEmail;
    private String role;
    private String address;
    private String phoneNumber;
}
