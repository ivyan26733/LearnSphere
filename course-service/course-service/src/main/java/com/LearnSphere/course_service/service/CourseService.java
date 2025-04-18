package com.LearnSphere.course_service.service;


import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course addCourse(Course course , MultipartFile imageFile) throws IOException {
        course.setImageName(imageFile.getName());
        course.setImageType(imageFile.getContentType());
        course.setImageData(imageFile.getBytes());

        System.out.println("Image size: " + course.getImageData().length); //

        return courseRepo.save(course);
    }


    public Course findCourseById(Integer id){
        return courseRepo.findById(id).orElse(null);
    }


    public String deleteCourse(Integer id) {
        Course course =  courseRepo.findById(id).orElse(null);
        courseRepo.delete(course);

        return "Successfully deleted";
    }

    public Course updateCourse(Integer id , Course course) {
        Course newCourse = courseRepo.findById(id).orElse(null);
        newCourse.setTitle(course.getTitle());
        newCourse.setCategory(course.getCategory());
        newCourse.setInstructorEmail(course.getInstructorEmail());
        newCourse.setModule(course.getModule());
        return courseRepo.save(newCourse);
    }

    public Course updateImage(Integer id, MultipartFile imageFile) throws IOException {
        Course course = courseRepo.findById(id).orElse(null);
        course.setImageName(imageFile.getName());
        course.setImageData(imageFile.getBytes());
        course.setImageType(imageFile.getContentType());
        return courseRepo.save(course);
    }

    public void deleteImage(Integer id) {
        Course course = courseRepo.findById(id).orElse(null);
        course.setImageType(null);
        course.setImageType(null);
        course.setImageData(null);
        courseRepo.save(course);
    }
}
