package com.LearnSphere.User_service.controller;


import com.LearnSphere.User_service.dto.UserProfileRequest;
import com.LearnSphere.User_service.model.InstructorProfile;
import com.LearnSphere.User_service.model.StudentProfile;
import com.LearnSphere.User_service.repo.InstructorRepo;
import com.LearnSphere.User_service.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private InstructorRepo instructorRepo;
    //    Common for both
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserProfileRequest request) {
        if ("STUDENT".equalsIgnoreCase(request.getRole())) {
            StudentProfile student = new StudentProfile();
            student.setUserName(request.getUserName());
            student.setUserEmail(request.getUserEmail());
            student.setRole(request.getRole());
            studentRepo.save(student);
        } else {
            InstructorProfile instructor = new InstructorProfile();
            instructor.setUserName(request.getUserName());
            instructor.setUserEmail(request.getUserEmail());
            instructor.setRole(request.getRole());
            instructorRepo.save(instructor);
        }

        return ResponseEntity.ok("Profile created");
    }
}
