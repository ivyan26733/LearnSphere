package com.LearnSphere.course_service.model;

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
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lesson = new ArrayList<>();
}



//Module {
//    id, title, orderIndex, courseId
//    List<Lesson>
//}
//You could also model:
//
//isFreePreview, duration, etc. for each Lesson
//
//A tagList for Course filtering/search

