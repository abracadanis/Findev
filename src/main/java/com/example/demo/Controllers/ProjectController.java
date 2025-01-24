package com.example.demo.Controllers;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Repos.ProjectRepo;
import com.example.demo.Services.ProjectService;
import com.example.demo.Services.UserService;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
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

@Tag(name = "application")
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
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id){
        List<UserEntity> list = projectService.getListOfUsers(id);
        for(int i = 0; i < list.size(); i++){
            userService.removeProject(id, list.get(i).getId());
        }
        projectRepo.deleteById(id);
        return new ResponseEntity<>("accepted", HttpStatus.ACCEPTED);
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
