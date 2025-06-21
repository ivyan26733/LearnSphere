package com.LearnSphere.course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CourseServiceDTO {
    private Integer id;
    private String title;
    private String category;
    private String instructorEmail;
    private List<ModuleDTO> modules;
    private boolean isPaid;
}
