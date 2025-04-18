package com.LearnSphere.course_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private String videoUrl;
    private String resourceLink;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}


//Lesson {
//    id, title, content (text), videoUrl, resourceLinks
//}