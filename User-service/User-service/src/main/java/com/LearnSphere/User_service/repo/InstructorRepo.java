package com.LearnSphere.User_service.repo;

import com.LearnSphere.User_service.model.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepo extends JpaRepository<InstructorProfile, Integer> {
    InstructorProfile findByUserEmail(String userEmail);
}
