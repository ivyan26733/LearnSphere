package com.LearnSphere.enrollment_service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDTO implements Serializable {
    private Integer id;
    private String title;
    private String category;
    private String instructorEmail;
    private boolean isPaid;
}
