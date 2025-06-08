package com.LearnSphere.course_service.controller;
import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.model.Lesson;
import com.LearnSphere.course_service.model.Module;
import com.LearnSphere.course_service.service.LessonService;
import com.LearnSphere.course_service.service.ModuleService;
import com.LearnSphere.course_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/protected")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping("/instructor/lesson/add")
    public ResponseEntity<ApiResponse<Lesson>> addCourse(@RequestBody Lesson lesson){
        lessonService.addLesson(lesson);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Added" , lesson , true));
    }

    @DeleteMapping("/instructor/lesson/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id){
        lessonService.deleteLesson(id);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Deleted" , null , true));
    }

    @PutMapping("/instructor/lesson/update/{id}")
    public ResponseEntity<ApiResponse<Lesson>> updateCourse(@PathVariable Integer id , @RequestBody Lesson lesson){
        Lesson newlessson = lessonService.updateLesson(id , lesson);
        return ResponseEntity.ok(new ApiResponse<>("SuccessFully Updated" , newlessson , true));
    }

    @GetMapping("/instructor/lesson/{lessonId}")
    public ResponseEntity<ApiResponse<Lesson>> getCourseById(@PathVariable Integer lessonId){
        Lesson lesson =  lessonService.findLessonById(lessonId);

        return ResponseEntity.ok(new ApiResponse<>("Fetched !!" , lesson , true));
    }
}
