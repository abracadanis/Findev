package com.example.demo.Controllers;

import com.example.demo.Services.ProjectService;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "application")
@RestController
@RequestMapping(value = "/projectapi")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    private void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProjectSo> createProject (@PathVariable("id") Long id, @RequestBody ProjectInputSo projectInputSo){
        return new ResponseEntity<>(projectService.createProject(projectInputSo, id), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectSo> getProjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<ProjectSo>> getProjects(){
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.ACCEPTED);
    }
}
