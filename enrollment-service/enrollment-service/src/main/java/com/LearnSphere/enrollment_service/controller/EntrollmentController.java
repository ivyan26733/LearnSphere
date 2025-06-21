package com.LearnSphere.enrollment_service.controller;

import com.LearnSphere.enrollment_service.dto.CourseDTO;
import com.LearnSphere.enrollment_service.model.Enrollment;
import com.LearnSphere.enrollment_service.service.EnrollmentService;
import com.LearnSphere.enrollment_service.utils.ApiResponse;
import com.LearnSphere.enrollment_service.utils.CourseServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protected/enrollments")
public class EntrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseServiceClient courseServiceClient;

    @PostMapping("/enroll")
    public ResponseEntity<ApiResponse<Enrollment>> enrollInCourse(@RequestParam Integer courseId,@AuthenticationPrincipal String email) {
        Enrollment enrollment = enrollmentService.enroll(email, courseId);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Enrolled" , enrollment , true));
    }

    @GetMapping("/enroll/courses")
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getenrolledCourse(@AuthenticationPrincipal String email){
        List<Enrollment> enrollments = enrollmentService.getenrolledCourses(email);
        List<Integer> courseIds = enrollments.stream()
                .map(Enrollment::getCourseId)
                .toList();

        List<CourseDTO> myCourses = courseServiceClient.getmyCourse(courseIds);


        return ResponseEntity.ok(new ApiResponse<>("Successfully Fetched !!",myCourses,true));
    }

    @GetMapping("/enroll/course/{courseId}")
    public ResponseEntity<ApiResponse<CourseDTO>> getcourseByCourseId(@PathVariable Integer courseId, @AuthenticationPrincipal String email){
        CourseDTO getCourse = enrollmentService.getCourseById(courseId,email);
        if(getCourse == null){
            return ResponseEntity.ok(new ApiResponse<>("Not Enrolled",getCourse,true));
        }
        return ResponseEntity.ok(new ApiResponse<>("Successfully Course Fetched",getCourse,true));
    }

//    Get courses in which user is enrolled
//    But also if user clicks on courses in which he is not enrolled or not paid , then he cannot proceed

}


//
//### ✅ Completed
//- [x] Auth Service
//- [x] API Gateway
//- [x] Eureka Server
//- [x] User Service
//- [x] Course Service
//- [x] Enrollment Service
//- [x] Getting All courses of user
//- [x] Lesson Access Guard
//

//### 🔜 Upcoming
//- [ ] Progress Tracking
//- [ ] Ratings & Reviews
//- [ ] Notifications
//- [ ] Certificates (Optional)
//- [ ] Payments (Optional)
