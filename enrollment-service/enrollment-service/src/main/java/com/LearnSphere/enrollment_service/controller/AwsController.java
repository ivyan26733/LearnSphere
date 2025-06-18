package com.LearnSphere.enrollment_service.controller;

import com.LearnSphere.enrollment_service.service.awsService;
import com.LearnSphere.enrollment_service.utils.ApiResponse;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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


    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) {
        S3Object s3Object = service.getFileObject(fileName);
        Resource resource = service.getFileResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        s3Object.getObjectMetadata().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + encodeFilename(fileName) + "\"")
                .body(resource);
    }


    private String encodeFilename(String filename) {
        try {
            return URLEncoder.encode(filename, StandardCharsets.UTF_8.toString())
                    .replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            return filename;
        }
    }


}
