package com.LearnSphere.enrollment_service.repo;

import com.LearnSphere.enrollment_service.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment , Integer> {
    void deleteByStudentEmail(String Email);
}
