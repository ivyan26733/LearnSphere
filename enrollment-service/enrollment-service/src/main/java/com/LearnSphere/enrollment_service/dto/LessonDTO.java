package com.LearnSphere.enrollment_service.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class LessonDTO implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private String videoUrl;
    private String resourceLink;
}