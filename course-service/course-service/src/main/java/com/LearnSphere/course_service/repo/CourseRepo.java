package com.LearnSphere.course_service.repo;

import com.LearnSphere.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepo extends JpaRepository<Course , Integer>{
}
