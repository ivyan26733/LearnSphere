package com.LearnSphere.course_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String category;
    private String imageName;
    private String imageType;
    private boolean isPaid;

//    @Lob
    @Column(name = "image_data", columnDefinition = "bytea")
    private byte[] imageData;


    private String instructorEmail;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Module> module = new ArrayList<>();

}





