package com.learnSphere.auth_service.controller;

import com.learnSphere.auth_service.service.UserEventPublisher;
import com.learnSphere.auth_service.service.UserService;
import com.learnSphere.auth_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected/auth")
public class ProtectedController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserEventPublisher userEventPublisher;

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer userId){
        String email = userService.getUserById(userId);
        if(!email.equals("FAIL")){
            userService.deleteUser(email);
            userEventPublisher.sendUserDeleteEvent(email);
            return ResponseEntity.ok(new ApiResponse<>("Success", "User Deleted",true));
        }

        return ResponseEntity.ok(new ApiResponse<>("Fail","User Not Present",false));
    }
}
