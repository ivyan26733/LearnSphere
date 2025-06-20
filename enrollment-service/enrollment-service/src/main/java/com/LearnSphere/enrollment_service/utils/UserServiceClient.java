package com.LearnSphere.enrollment_service.utils;

import com.LearnSphere.enrollment_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="USER-SERVICE")
public interface UserServiceClient {
    @GetMapping("/api/protected/student/email/{email}")
    UserDTO getUserByEmail(@PathVariable String email);
}
