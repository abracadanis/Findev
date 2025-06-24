package com.example.demo.Controllers;

import com.example.demo.Repos.ProjectRepo;
import com.example.demo.Services.ProjectService;
import com.example.demo.Services.UserService;
import com.example.demo.Services.so.project.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Project")
@RestController
@RequestMapping(value = "/project")
public class ProjectController {
//TODO remove comments
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

    @PostMapping(value = "/", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProjectWithImageNameResponseSo> createProject (
            @Parameter(
                    description = "JSON data payload",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectInputSo.class))
            )
            @RequestPart(name = "body")
            @Valid
            ProjectInputSo projectInputSo,
            @Parameter(
                    description = "Is this project a draft?",
                    required = true,
                    schema = @Schema(type = "boolean")
            )
            @RequestParam("isDraft") Boolean isDraft,
            @Parameter(
                    description = "Image file to upload",
                    content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE)
            )
            @RequestPart(name = "file", required = false) MultipartFile file) throws Exception {
        return new ResponseEntity<>(projectService.createProject(projectInputSo, isDraft, file), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProjectWithFullImageResponseSo> getProjectById(@PathVariable("id") Long id) throws IOException {
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/clean")
    public ResponseEntity<List<ProjectWithImageNameResponseSo>> getCleanProjects(){
        return new ResponseEntity<>(projectService.getProjects(false), HttpStatus.OK);
    }

    @GetMapping(value = "/drafts")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProjectWithImageNameResponseSo>> getDraftsProjects(){
        return new ResponseEntity<>(projectService.getProjects(true), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProjectWithImageNameResponseSo>> getProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProjectWithImageNameResponseSo> deleteProject(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.ACCEPTED);
    }

//    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<String> setImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile file) throws IOException {
//        return new ResponseEntity<>(projectService.saveImageFile(id, file), HttpStatus.ACCEPTED);
//    }

//    @GetMapping("/{id}/image")
//    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) throws IOException {
//        return new ResponseEntity<>(new ByteArrayResource(projectService.getImageFile(id)), HttpStatus.OK);
//    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProjectWithImageNameResponseSo> updateProjectInfo(
            @PathVariable("id") Long id,
            @Parameter(
                    description = "JSON data payload",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProjectInputSo.class))
            )
            @RequestPart(name = "body")
            @Valid
            ProjectUpdateSo project,
            @Parameter(
                    description = "Is this project a draft?",
                    required = true,
                    schema = @Schema(type = "boolean")
            )
            @RequestParam("isDraft") Boolean isDraft,
            @Parameter(
                    description = "Image file to upload",
                    content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE)
            )
            @RequestPart(name = "file", required = false) MultipartFile file) throws Exception {
        return new ResponseEntity<>(projectService.updateProject(project, id, isDraft, file), HttpStatus.ACCEPTED);
    }
}
