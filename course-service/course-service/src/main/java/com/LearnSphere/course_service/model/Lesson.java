package com.LearnSphere.course_service.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private Integer id;
    private String title;
    private String content;
    private String videoUrl;
    private String resourceLink;
}


//Lesson {
//    id, title, content (text), videoUrl, resourceLinks
//}