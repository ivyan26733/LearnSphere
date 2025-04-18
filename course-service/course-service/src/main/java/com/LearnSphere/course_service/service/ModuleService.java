package com.LearnSphere.course_service.service;

import com.LearnSphere.course_service.model.Module;
import com.LearnSphere.course_service.repo.ModuleRepo;
import com.LearnSphere.course_service.repo.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepo moduleRepo;

    public Module addModule(Module module) {
        return moduleRepo.save(module);
    }


    public String deleteModule(Integer id) {
        Module module =  moduleRepo.findById(id).orElse(null);
        moduleRepo.delete(module);

        return "Successfully deleted";
    }

    public Module updateModule(Integer id , Module module) {
        Module newModule = moduleRepo.findById(id).orElse(null);
        newModule.setTitle(module.getTitle());
        newModule.setOrderIndex(module.getOrderIndex());
        newModule.setCourse(module.getCourse());
        newModule.setLesson(module.getLesson());

        return moduleRepo.save(newModule);
    }
}
