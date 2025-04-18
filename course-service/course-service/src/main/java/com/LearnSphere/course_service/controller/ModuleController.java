package com.LearnSphere.course_service.controller;


import com.LearnSphere.course_service.model.Course;
import com.LearnSphere.course_service.service.CourseService;
import com.LearnSphere.course_service.service.ModuleService;
import com.LearnSphere.course_service.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.LearnSphere.course_service.model.Module;


@RestController
@RequestMapping("/protected/instructor")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/module/add")
    public ResponseEntity<ApiResponse<Module>> addCourse(@RequestBody Module module){
        moduleService.addModule(module);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Added" , module , true));
    }

    @DeleteMapping("/module/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id){
        moduleService.deleteModule(id);
        return ResponseEntity.ok(new ApiResponse<>("Successfully Deleted" , null , true));
    }

    @PutMapping("/module/update/{id}")
    public ResponseEntity<ApiResponse<Module>> updateCourse(@PathVariable Integer id , @RequestBody Module module){
        Module newmodule = moduleService.updateModule(id , module);
        return ResponseEntity.ok(new ApiResponse<>("SuccessFully Updated" , newmodule , true));
    }
}
