package com.LearnSphere.enrollment_service.utils;


import com.LearnSphere.enrollment_service.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="COURSE-SERVICE")
public interface CourseServiceClient {
    @GetMapping("protected/users/course/{courseId}")
    CourseDTO getCourseById(@PathVariable Integer courseId);

    @PostMapping("protected/users/my-courses")
    List<CourseDTO> getmyCourse(@RequestBody List<Integer> courseIds);

}
