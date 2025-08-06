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
import java.util.*;
import java.util.stream.Collectors;

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

    public List<CourseServiceDTO> getUserCourse(List<Integer> courseIds) {
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

    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    public List<Course> getRandomEntities(int count) {
        Integer minId = courseRepo.findMinId();
        Integer maxId = courseRepo.findMaxId();

        if (minId == null || maxId == null || maxId < minId) {
            return Collections.emptyList();
        }

        Set<Integer> allRandomIds = new HashSet<>();
        Random random = new Random();

        while (allRandomIds.size() < count) {
            Integer randomId = minId + random.nextInt((maxId - minId + 1));
            allRandomIds.add(randomId);
        }

        List<Course> result = courseRepo.findByIdIn(new ArrayList<>(allRandomIds));

        // Retry if fewer results are returned
        int retries = 5;
        while (result.size() < count && retries-- > 0) {
            Set<Integer> foundIds = result.stream()
                    .map(Course::getId)
                    .collect(Collectors.toSet());

            Set<Integer> missingIds = generateAdditionalRandomIds(minId, maxId, count - result.size(), foundIds);
            if (missingIds.isEmpty()) break;

            List<Course> additional = courseRepo.findByIdIn(new ArrayList<>(missingIds));
            result.addAll(additional);
        }

        // Optional: If more than required, trim list
        return result.size() > count ? result.subList(0, count) : result;
    }

    private Set<Integer> generateAdditionalRandomIds(Integer minId, Integer maxId, int count, Set<Integer> exclude) {
        Set<Integer> newIds = new HashSet<>();
        Random random = new Random();
        int tries = 0;

        while (newIds.size() < count && tries < count * 5) { // prevent infinite loop
            Integer randomId = minId + random.nextInt((maxId - minId + 1));
            if (!exclude.contains(randomId)) {
                newIds.add(randomId);
            }
            tries++;
        }

        return newIds;
    }

}

