package com.LearnSphere.enrollment_service.service;

import com.LearnSphere.enrollment_service.dto.CourseDTO;
import com.LearnSphere.enrollment_service.dto.UserDTO;
import com.LearnSphere.enrollment_service.model.Enrollment;
import com.LearnSphere.enrollment_service.repo.EnrollmentRepo;
import com.LearnSphere.enrollment_service.utils.CourseServiceClient;
import com.LearnSphere.enrollment_service.utils.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnrollmentService {
    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private CourseServiceClient courseServiceClient;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    public Enrollment enroll(String studentEmail, Integer courseId) {
        // 1. Fetch user by email from JWT
        UserDTO student = userServiceClient.getUserByEmail(studentEmail);
        System.out.println(student.getRole());
        if (!"STUDENT".equals(student.getRole())) {
            throw new RuntimeException("Only students can enroll!");
        }

        // 2. Fetch course details
        CourseDTO course = courseServiceClient.getCourseById(courseId);

        // 3. Create Enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(student.getUserId());
        enrollment.setCourseId(course.getId());
        enrollment.setStudentEmail(student.getUserEmail());
        enrollment.setCourseTitle(course.getTitle());
        enrollment.setCourseCategory(course.getCategory());
        enrollment.setInstructorEmail(course.getInstructorEmail());
        enrollment.setPaid(course.isPaid());
        enrollment.setEnrollmentDate(LocalDateTime.now());

        enrollmentRepo.save(enrollment);
        return enrollment;
    }
    public String removeEnrollment(Integer enrollmentId) {
        enrollmentRepo.deleteById(enrollmentId);
        return "SUCCESS";
    }

}
