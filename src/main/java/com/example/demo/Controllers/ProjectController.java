package com.example.demo.Controllers;

import com.example.demo.Services.ProjectService;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProjectSo> getProjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<ProjectSo>> getProjects(){
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProject(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<String> setImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile file) {
        return new ResponseEntity<>(projectService.saveImageFile(id, file), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(projectService.getImageFile(id), HttpStatus.OK);
    }
}
