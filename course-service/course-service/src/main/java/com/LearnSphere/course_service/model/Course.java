package com.LearnSphere.course_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private String title;
    private String category;
    private String imageName;
    private String imageType;
    private byte[] imageData;
    private String instructorEmail;

    @OneToMany
    private List<Module> module;
}




//Course {
//    id, title, description, category, imageUrl, instructorEmail
//    List<Module>
//}

