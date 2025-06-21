package com.LearnSphere.User_service.controller;


import com.LearnSphere.User_service.dto.UserProfileRequest;
import com.LearnSphere.User_service.model.InstructorProfile;
import com.LearnSphere.User_service.model.StudentProfile;
import com.LearnSphere.User_service.repo.InstructorRepo;
import com.LearnSphere.User_service.repo.StudentRepo;
import com.LearnSphere.User_service.service.ProfileService;
import com.LearnSphere.User_service.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/protected/instructor")
public class InstructorController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/updateProfile")
    public ResponseEntity<ApiResponse<InstructorProfile>> updateInstructorProfile(@RequestBody InstructorProfile profile){
        InstructorProfile updatedProfile = profileService.updateProfile(profile);
        ApiResponse<InstructorProfile> apiResponse = new ApiResponse<>("Profile Updated" , updatedProfile , true);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<InstructorProfile>>addProduct(@RequestPart("instructor") String instructorJson ,@RequestPart("image") MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InstructorProfile instructorProfile = objectMapper.readValue(instructorJson , InstructorProfile.class);

        InstructorProfile addInstructorProfile = this.profileService.addInstructor(instructorProfile , imageFile);
        ApiResponse<InstructorProfile> res = new ApiResponse("Product added successfully!", addInstructorProfile , true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getImageByStudentId(@PathVariable int userId){
        StudentProfile studentProfile = profileService.findStudentById(userId);
        byte[] imageFile = studentProfile.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(studentProfile.getImageType())).body(imageFile);
    }

}