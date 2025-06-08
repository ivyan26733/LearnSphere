package com.LearnSphere.course_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CourseServiceDTO {
    private Integer id;
    private String title;
    private String category;
    private String instructorEmail;
    private boolean isPaid;
}
