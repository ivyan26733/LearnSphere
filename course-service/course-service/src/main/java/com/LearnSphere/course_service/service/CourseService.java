package com.LearnSphere.course_service.service;


import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course addCourse(Course course) {
        return courseRepo.save(course);
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
}
