package com.LearnSphere.enrollment_service.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class LessonDTO {
    private Integer id;
    private String title;
    private String content;
    private String videoUrl;
    private String resourceLink;
}