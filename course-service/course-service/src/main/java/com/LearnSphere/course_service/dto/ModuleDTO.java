package com.LearnSphere.course_service.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {
    private Integer id;
    private String title;
    private List<LessonDTO> lessons;
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

