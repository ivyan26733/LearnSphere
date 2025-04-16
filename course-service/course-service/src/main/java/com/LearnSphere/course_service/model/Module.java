package com.LearnSphere.course_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    private Integer id;
    private String title;
    private Integer orderIndex;
    private Integer courseId;

    @OneToMany
    private List<Lesson> lesson;
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

