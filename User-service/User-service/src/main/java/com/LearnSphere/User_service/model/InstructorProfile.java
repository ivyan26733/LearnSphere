package com.LearnSphere.User_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    @Column(nullable = false,unique = true)
    private String userEmail;  //Cannot Edit
    private String role;  //Cannot Edit
    private String address;
    private String phoneNumber;
    private String bio;
    private List<String> skills;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,String> socialLinks;
    private Double ratings;       //For Teacher
    private Integer ratingCount;  //For Teacher
    private List<String> certifications;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
