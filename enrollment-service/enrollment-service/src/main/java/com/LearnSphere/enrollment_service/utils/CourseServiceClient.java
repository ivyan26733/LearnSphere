package com.LearnSphere.enrollment_service.utils;


import com.LearnSphere.enrollment_service.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COURSE-SERVICE")
public interface CourseServiceClient {
    @GetMapping("protected/users/course/{courseId}")
    CourseDTO getCourseById(@PathVariable Integer courseId);
}
