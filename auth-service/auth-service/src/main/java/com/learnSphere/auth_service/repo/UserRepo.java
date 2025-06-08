package com.learnSphere.auth_service.repo;


import com.learnSphere.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
    void deleteByUserEmail(String email);
}
