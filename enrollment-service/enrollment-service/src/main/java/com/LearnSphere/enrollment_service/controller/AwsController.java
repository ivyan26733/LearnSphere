package com.LearnSphere.enrollment_service.controller;

import com.LearnSphere.enrollment_service.service.awsService;
import com.LearnSphere.enrollment_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/protected/instructor/files")
public class AwsController {
    @Autowired
    private awsService service;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadFile(@RequestParam("file")MultipartFile file){
        String fileurl = service.uploadFile(file);
        return ResponseEntity.ok(new ApiResponse<>("Uploaded",fileurl,true));
    }

}
