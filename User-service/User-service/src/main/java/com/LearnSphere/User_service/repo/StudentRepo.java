package com.LearnSphere.User_service.repo;

import com.LearnSphere.User_service.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends JpaRepository<StudentProfile, Integer> {
    StudentProfile findByUserEmail(String userEmail);

    void deleteByUserEmail(String email);
}
