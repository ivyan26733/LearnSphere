package com.LearnSphere.enrollment_service.service;

import com.LearnSphere.enrollment_service.dto.CourseDTO;
import com.LearnSphere.enrollment_service.dto.UserDTO;
import com.LearnSphere.enrollment_service.model.Enrollment;
import com.LearnSphere.enrollment_service.repo.EnrollmentRepo;
import com.LearnSphere.enrollment_service.utils.CourseServiceClient;
import com.LearnSphere.enrollment_service.utils.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Enrollment> getenrolledCourses(String email){
        List<Enrollment> enrollement = enrollmentRepo.findAllByStudentEmail(email);
        return enrollement;
    }

    public CourseDTO getCourseById(Integer courseId, String email) {

        List<Enrollment> enrollment = enrollmentRepo.findAllByStudentEmail(email);

        for(Enrollment enroll : enrollment){
            if(enroll.getCourseId().equals(courseId)){
                CourseDTO courseDTO = courseServiceClient.getCourseById(courseId);
                return courseDTO;
            }

        }
        return null;
    }

    @Transactional
    public Enrollment giveRating(Double rating, Integer courseId, String email) {
        Enrollment enrollment = enrollmentRepo.findByStudentEmailAndCourseId(email, courseId);

        enrollment.setRatingGiven(rating);

        return enrollment;
    }
}
