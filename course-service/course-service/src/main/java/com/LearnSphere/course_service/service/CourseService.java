package com.LearnSphere.course_service.service;


import com.LearnSphere.course_service.dto.CourseServiceDTO;
import com.LearnSphere.course_service.dto.LessonDTO;
import com.LearnSphere.course_service.dto.ModuleDTO;
import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.repo.CourseRepo;
import com.LearnSphere.course_service.repo.LessonRepo;
import com.LearnSphere.course_service.repo.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CourseService {



    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModuleRepo moduleRepo;

    @Autowired
    private LessonRepo lessonRepo;

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

    public List<CourseServiceDTO> getAllCourse(List<Integer> courseIds) {
        List<Course> courses = courseRepo.findAllById(courseIds);

        List<CourseServiceDTO> courseDtos = courses.stream().map(course -> {
            CourseServiceDTO dto = new CourseServiceDTO();
            dto.setId(course.getId());
            dto.setTitle(course.getTitle());
            dto.setCategory(course.getCategory());
            dto.setInstructorEmail(course.getInstructorEmail());

            List<ModuleDTO> modules = moduleRepo.findByCourseId(course.getId())
                    .stream().map(module -> {
                        ModuleDTO mDto = new ModuleDTO();
                        mDto.setId(module.getId());
                        mDto.setTitle(module.getTitle());

                        List<LessonDTO> lessons = lessonRepo.findByModuleId(module.getId())
                                .stream().map(lesson -> new LessonDTO(
                                        lesson.getId(),
                                        lesson.getTitle(),
                                        lesson.getContent(),
                                        lesson.getVideoUrl(),
                                        lesson.getResourceLink()
                                )).toList();

                        mDto.setLessons(lessons);
                        return mDto;
                    }).toList();

            dto.setModules(modules);
            return dto;
        }).toList();
        return courseDtos;
    }
}

