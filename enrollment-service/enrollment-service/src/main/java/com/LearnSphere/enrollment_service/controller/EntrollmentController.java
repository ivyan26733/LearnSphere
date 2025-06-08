package com.LearnSphere.enrollment_service.controller;

import com.LearnSphere.enrollment_service.model.Enrollment;
import com.LearnSphere.enrollment_service.service.EnrollmentService;
import com.LearnSphere.enrollment_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class EntrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll/student")
    public ResponseEntity<ApiResponse<Enrollment>> enrollInCourse(@RequestParam Integer courseId,@AuthenticationPrincipal String email) {
        Enrollment enrollment = enrollmentService.enroll(email, courseId);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Enrolled" , enrollment , true));
    }

}
