package com.LearnSphere.course_service.repo;

import com.LearnSphere.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepo extends JpaRepository<Course , Integer>{
    @Query("SELECT MIN(c.id) FROM Course c")
    Integer findMinId();

    @Query("SELECT MAX(c.id) FROM Course c")
    Integer findMaxId();

    List<Course> findByIdIn(List<Integer> ids);
}
