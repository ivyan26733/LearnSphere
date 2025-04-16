package com.LearnSphere.User_service.service;


import com.LearnSphere.User_service.model.InstructorProfile;
import com.LearnSphere.User_service.model.StudentProfile;
import com.LearnSphere.User_service.repo.InstructorRepo;
import com.LearnSphere.User_service.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private InstructorRepo instructorRepo;

    public StudentProfile updateProfile(Integer userId,StudentProfile profile) {
        StudentProfile studentProfile = studentRepo.findById(userId).orElse(null);
        studentProfile.setUserName(profile.getUserName());
        studentProfile.setAddress(profile.getAddress());
        studentProfile.setPhoneNumber(profile.getPhoneNumber());
        studentProfile.setBio(profile.getBio());
        studentProfile.setSkills(profile.getSkills());
        studentProfile.setSocialLinks(profile.getSocialLinks());
        studentProfile.setCertifications(profile.getCertifications());
        return studentRepo.save(studentProfile);
    }

    public InstructorProfile updateProfile(InstructorProfile profile) {
        return instructorRepo.findByUserEmail(profile.getUserEmail());
    }


    public StudentProfile addStudent(StudentProfile studentProfile, MultipartFile imageFile) throws IOException {
        studentProfile.setImageName(imageFile.getName());
        studentProfile.setImageType(imageFile.getContentType());
        studentProfile.setImageData(imageFile.getBytes());

        return studentRepo.save(studentProfile);
    }

    public StudentProfile findStudentById(int userId) {
        return studentRepo.findById(userId).orElse(null);
    }

    public InstructorProfile findInstructorById(int userId) {
        return instructorRepo.findById(userId).orElse(null);
    }

    public InstructorProfile addInstructor(InstructorProfile instructorProfile, MultipartFile imageFile) throws IOException {
        instructorProfile.setImageName(imageFile.getName());
        instructorProfile.setImageType(imageFile.getContentType());
        instructorProfile.setImageData(imageFile.getBytes());

        return instructorRepo.save(instructorProfile);
    }
}
