package com.example.demo.Controllers;

import com.example.demo.Services.ProjectService;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "application")
@RestController
@RequestMapping(value = "/api")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    private void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectSo> createProject (@RequestBody ProjectInputSo projectInputSo){
        return new ResponseEntity<>(projectService.saveProject(projectInputSo), HttpStatus.CREATED);
    }
}
