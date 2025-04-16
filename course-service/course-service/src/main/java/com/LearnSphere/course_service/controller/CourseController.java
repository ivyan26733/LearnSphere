package com.LearnSphere.course_service.controller;


import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.service.CourseService;
import com.LearnSphere.course_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protected/instructor")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course/add")
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Added" , course , true));
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Deleted" , null , true));
    }

    @PutMapping("/course/update/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer id , @RequestBody Course course){
        Course newcourse = courseService.updateCourse(id , course);
        return ResponseEntity.ok(new ApiResponse<>("SuccessFully Updated" , newcourse , true));
    }


}


//Create Update Delete Courses