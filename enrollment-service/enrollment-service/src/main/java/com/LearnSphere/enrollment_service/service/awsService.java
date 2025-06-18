package com.LearnSphere.enrollment_service.service;

import com.LearnSphere.enrollment_service.model.Enrollment;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class awsService {
    @Autowired
    private AmazonS3 s3client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    private EnrollmentService enrollmentService;


    public String uploadFile(MultipartFile file){

        try{
            String fileName = generateFileName(file.getOriginalFilename());

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3client.putObject(new PutObjectRequest(bucketName,fileName,file.getInputStream(),metadata));
            return s3client.getUrl(bucketName,fileName).toString();
        }catch (IOException exception){
            throw new RuntimeException("Failed to upload file to s3",exception);
        }
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "-" + originalFileName;
    }


    public S3Object getFileObject(String fileName) {
        return s3client.getObject(bucketName, fileName);
    }

    public Resource getFileResource(String fileName) {
        S3Object s3Object = getFileObject(fileName);
        return new InputStreamResource(s3Object.getObjectContent()) {
            @Override
            public String getFilename() {
                return fileName;
            }
            @Override
            public long contentLength() {
                return s3Object.getObjectMetadata().getContentLength();
            }
        };
    }



}
