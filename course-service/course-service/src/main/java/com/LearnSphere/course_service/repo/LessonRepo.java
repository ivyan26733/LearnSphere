package com.LearnSphere.course_service.repo;

import com.LearnSphere.course_service.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends JpaRepository<Lesson,Integer> {
}
