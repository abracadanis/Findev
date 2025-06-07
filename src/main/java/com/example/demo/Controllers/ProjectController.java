package com.example.demo.Controllers;

import com.example.demo.Repos.ProjectRepo;
import com.example.demo.Services.ProjectService;
import com.example.demo.Services.UserService;
import com.example.demo.Services.so.project.ProjectInputSo;
import com.example.demo.Services.so.project.ProjectSo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Project")
@RestController
@RequestMapping(value = "/projectapi")
public class ProjectController {

    private ProjectService projectService;

    private ProjectRepo projectRepo;

    private UserService userService;

    @Autowired

    private void setUserService(UserService userService) { this.userService = userService;}

    @Autowired
    private void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @Autowired
    private void setProjectRepo(ProjectRepo projectRepo) { this.projectRepo = projectRepo; }

    @PostMapping("/")
    public ResponseEntity<ProjectSo> createProject (@RequestBody ProjectInputSo projectInputSo, @RequestParam Boolean isDraft){
        return new ResponseEntity<>(projectService.createProject(projectInputSo, isDraft), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectSo> getProjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<ProjectSo>> getProjects(@RequestParam Boolean isDraft){
        return new ResponseEntity<>(projectService.getProjects(isDraft), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectSo> deleteProject(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> setImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile file) throws IOException {
        return new ResponseEntity<>(projectService.saveImageFile(id, file), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(new ByteArrayResource(projectService.getImageFile(id)), HttpStatus.OK);
    }
}
