package com.LearnSphere.course_service.repo;


import com.LearnSphere.course_service.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepo extends JpaRepository<Module,Integer> {
}
