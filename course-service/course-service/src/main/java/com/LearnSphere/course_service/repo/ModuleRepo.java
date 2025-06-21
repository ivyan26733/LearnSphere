package com.LearnSphere.course_service.repo;


import com.LearnSphere.course_service.model.Lesson;
import com.LearnSphere.course_service.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepo extends JpaRepository<Module,Integer> {
    List<Module> findByCourseId(Integer id);
}
