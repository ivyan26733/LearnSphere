package com.LearnSphere.course_service.controller;
import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.service.CourseService;
import com.LearnSphere.course_service.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/protected/instructor")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping("/course/add")
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestPart("course")  String courseJson , @RequestPart("image") MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Course course  = objectMapper.readValue(courseJson , Course.class);

        Course addCourse = this.courseService.addCourse(course , imageFile);
        ApiResponse<Course> res = new ApiResponse("Product added successfully!", addCourse , true);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/course/{courseId}/image")
    public ResponseEntity<byte[]> getImageByCourseId(@PathVariable int courseId) {
        Course course = courseService.findCourseById(courseId);
        byte[] imageFile = course.getImageData();

        if (imageFile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(course.getImageType()))
                .body(imageFile);
    }

    @DeleteMapping("/course/{courseId}/image")
    public ResponseEntity<ApiResponse<String>> deleteImage(@PathVariable Integer courseId){
        courseService.deleteImage(courseId);
        return ResponseEntity.ok(new ApiResponse<>("Image Deleted" , null , true));
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Deleted" , null , true));
    }

    @PutMapping("/course/update/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer id , @RequestBody Course course){
        Course newcourse = courseService.updateCourse(id , course);
        return ResponseEntity.ok(new ApiResponse<>("SuccessFully Updated" , newcourse , true));
    }

    @PutMapping("/course/update/image/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer id , @RequestPart("image") MultipartFile imageFile) throws IOException {
        Course newcourse = courseService.updateImage(id , imageFile);
        return ResponseEntity.ok(new ApiResponse<>("Image SuccessFully Updated" , newcourse , true));
    }

}
