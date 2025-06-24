package com.example.demo.Services;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.project.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProjectService{

    private static final String PROJECT_IMAGES_PATH = "./src/main/resources/projectimages";

    private ProjectRepo projectRepo;

    private UserRepo userRepo;

    private ProjectMapper projectMapper;

    private UserMapper userMapper;

    private UserService userService;

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ProjectWithImageNameResponseSo createProject(ProjectInputSo projectInputSo, Boolean isDraft, MultipartFile file) throws Exception {
        if(projectInputSo.getOwnerId() != null && userRepo.findUserById(projectInputSo.getOwnerId()).isPresent()) {
            ProjectEntity projectEntity = projectMapper.mapToEntity(projectInputSo);
            projectEntity.setOwner(userRepo.findUserById(projectInputSo.getOwnerId()).get());
            projectEntity.getUsers().add(projectEntity.getOwner());
            projectEntity.setDraft(isDraft);
            if(file != null && !file.isEmpty()) {
                imageTypeCheck(file);
                String fileName = imageService.saveFileToFileSystem(file);
                projectEntity.setImageFileName(fileName);
            }

            projectEntity = projectRepo.save(projectEntity);

            return projectMapper.mapToProjectWithImageNameResponse(projectEntity);
        } else {
            throw new NoSuchElementException(String.format("User with id '%d' not found", projectInputSo.getOwnerId()));
        }
    }

    public ProjectWithFullImageResponseSo getProjectById(Long id) throws IOException {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(() ->
                        new NoSuchElementException(String.format("Project with id '%d' not found", id))
                );
        ProjectWithFullImageResponseSo so = projectMapper.mapToProjectWithFullImageResponse(project);
        if(project.getImageFileName()!=null){
            byte[] ba = imageService.getFileAsByteArray(project.getImageFileName());
            so.setImageByteArray(ba);
        }
        return so;
    }

    public List<ProjectWithImageNameResponseSo> getProjects(Boolean isDraft){
        return projectMapper.mapListToSo(projectRepo.findProjectsByDraft(isDraft));
    }

    public List<ProjectWithImageNameResponseSo> getAllProjects() {
        return projectMapper.mapListToSo(projectRepo.findAll());
    }

    public List<Long> getListOfUsers(Long id){
        return projectRepo.findUserIdsByProjectId(id);
    }

//    public String saveImageFile(Long projectId, MultipartFile file) throws IOException {
//        if(projectRepo.findProjectById(projectId).isPresent()){
//            ProjectEntity project = projectRepo.findProjectById(projectId).get();
//
//            ImageEntity imageEntity = new ImageEntity(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes()));
//            imageEntity.setProject(project);
//            imageRepo.save(imageEntity);
//
//            project.setImage(imageEntity);
//            projectRepo.save(project);
//
//            if(project.getImage() != null) {
//                return "file uploaded successfully : " + file.getOriginalFilename();
//            } else {
//                return null;
//            }
//        } else return "Project doesnt exist";
//    }

//    public byte[] getImageFile(Long id) throws IOException {
//        ImageEntity image = imageRepo.findById(id).get();
//        byte[] file = ImageUtils.decompressImage(image.getImage());
//        return file;
//    }

    @Transactional
    public ProjectWithImageNameResponseSo deleteProject(Long id) {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );
        projectRepo.deleteById(id);

        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

    public ProjectWithImageNameResponseSo updateProject(ProjectUpdateSo projectInput, Long id, Boolean isDraft, MultipartFile file) throws Exception {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );

        projectMapper.updateProject(project, projectInput);
        if(file != null && !file.isEmpty()) {
            imageTypeCheck(file);
            if(project.getImageFileName()!=null){
                imageService.deleteFileFromFileSystem(project.getImageFileName());
                String fileName = imageService.saveFileToFileSystem(file);
                project.setImageFileName(fileName);
            }
        }
        project.setDraft(isDraft);
        project = projectRepo.save(project);
        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

    private void imageTypeCheck(MultipartFile file){
        if(!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("Only files can be uploaded.");
        }
    }

}
