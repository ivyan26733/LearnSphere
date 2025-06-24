package com.LearnSphere.course_service.controller;


import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.service.CourseService;
import com.LearnSphere.course_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public")
public class GetCourses {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(){
        List<Course> courseList = courseService.getAllCourse();

        if(courseList.size()!=0){
            return ResponseEntity.ok(new ApiResponse<>("Courses Fetched",courseList,true));
        }

        return ResponseEntity.ok(new ApiResponse<>("No Courses Found",null,false));
    }
}
