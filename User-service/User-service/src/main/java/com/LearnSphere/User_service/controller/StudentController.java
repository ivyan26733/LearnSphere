package com.LearnSphere.User_service.controller;


import com.LearnSphere.User_service.model.StudentProfile;
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
@RequestMapping("/protected/student")
public class StudentController {
    @Autowired
    private ProfileService profileService;


    @PostMapping("/userBio")
    public ResponseEntity<ApiResponse<StudentProfile>> updateStudentProfile(@RequestBody StudentProfile profile){
        StudentProfile updatedProfile = profileService.updateProfile(profile);
        ApiResponse<StudentProfile> apiResponse = new ApiResponse<>("Profile Updated" , updatedProfile , true);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<StudentProfile>> addProduct(@RequestPart("student") String studentJson ,@RequestPart("image") MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentProfile studentProfile = objectMapper.readValue(studentJson , StudentProfile.class);

        StudentProfile addStudentProfile = this.profileService.addStudent(studentProfile , imageFile);
        ApiResponse<StudentProfile> res = new ApiResponse("Product added successfully!", addStudentProfile , true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getImageByStudentId(@PathVariable int userId){
        StudentProfile studentProfile = profileService.findStudentById(userId);
        byte[] imageFile = studentProfile.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(studentProfile.getImageType())).body(imageFile);
    }



}






