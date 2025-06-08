package com.LearnSphere.course_service.service;

import com.LearnSphere.course_service.model.Lesson;
import com.LearnSphere.course_service.model.Module;
import com.LearnSphere.course_service.repo.LessonRepo;
import com.LearnSphere.course_service.repo.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    private LessonRepo lessonRepo;

    public Lesson addLesson(Lesson lesson) {
        return lessonRepo.save(lesson);
    }


    public String deleteLesson(Integer id) {
        Lesson lesson =  lessonRepo.findById(id).orElse(null);
        lessonRepo.delete(lesson);

        return "Successfully deleted";
    }

    public Lesson updateLesson(Integer id , Lesson lesson) {
        Lesson newlesson = lessonRepo.findById(id).orElse(null);
        newlesson.setTitle(lesson.getTitle());
        newlesson.setContent(lesson.getContent());
        newlesson.setVideoUrl(lesson.getVideoUrl());
        newlesson.setResourceLink(lesson.getResourceLink());

        return lessonRepo.save(newlesson);
    }

    public Lesson findLessonById(Integer lessonId) {
        return lessonRepo.findById(lessonId).orElse(null);
    }
}
