package com.learnSphere.auth_service.utils;

import com.learnSphere.auth_service.dto.UserProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient{

    @PostMapping("/user/create")
    ResponseEntity<String> createUserProfile(@RequestBody UserProfileRequest userProfileRequest);
}
