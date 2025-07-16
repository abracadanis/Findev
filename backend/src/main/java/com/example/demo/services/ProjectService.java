package com.example.demo.services;

import com.example.demo.model.ApplicationUserPermission;
import com.example.demo.model.ProjectEntity;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.model.UserEntity;
import com.example.demo.repositories.ProjectRepository;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.so.project.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProjectService{
    private ProjectRepository projectRepository;

    private UserRepository userRepository;

    private ProjectMapper projectMapper;

    private UserMapper userMapper;

    private UserService userService;

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @Autowired
    public void setProjectRepo(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setUserRepo(UserRepository userRepository){
        this.userRepository = userRepository;
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

    public ProjectWithImageNameResponseSo createProject(Project projectInputSo, Boolean isDraft, MultipartFile file) throws Exception {
        UserDetails ud = getUserDetails();
        ProjectEntity projectEntity = projectMapper.mapToEntity(projectInputSo);
        projectEntity.setOwner(userRepository.findUserByUsernameAndDeletedIsFalse(ud.getUsername()).get());
        projectEntity.getUsers().add(projectEntity.getOwner());
        projectEntity.setDraft(isDraft);
        if(file != null && !file.isEmpty()) {
            imageTypeCheck(file);
            String fileName = imageService.saveFileToFileSystem(file);
            projectEntity.setImageFileName(fileName);
        }

        projectEntity = projectRepository.save(projectEntity);

        return projectMapper.mapToProjectWithImageNameResponse(projectEntity);
    }

    public ProjectWithFullImageResponseSo getProjectById(Long id) throws IOException {
        ProjectEntity project = projectRepository.findProjectById(id).orElseThrow(() ->
                        new NoSuchElementException(String.format("Project with id '%d' not found", id))
                );
        ProjectWithFullImageResponseSo so = projectMapper.mapToProjectWithFullImageResponse(project);
        if(project.getImageFileName()!=null){
            byte[] ba = imageService.getFileAsByteArray(project.getImageFileName());
            so.setImageByteArray(ba);
        }
        return so;
    }

    public List<ProjectWithImageNameResponseSo> getProjects(Boolean isDraft) {
        return projectMapper.mapListToSo(projectRepository.findProjectsByDraft(isDraft));
    }

    public List<ProjectWithImageNameResponseSo> getAllProjects() {
        return projectMapper.mapListToSo(projectRepository.findAll());
    }

    public List<Long> getListOfUsers(Long id){
        return projectRepository.findUserIdsByProjectId(id);
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
    public ProjectWithImageNameResponseSo deleteProject(Long id) throws IllegalAccessException {
        UserDetails ud = getUserDetails();
        UserEntity currentUser = userRepository.findUserByUsernameAndDeletedIsFalse(ud.getUsername()).get();
        ProjectEntity project = projectRepository.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );
        if(!currentUser.getOwnedProjects().contains(project) && !ud.getAuthorities().contains(new SimpleGrantedAuthority(ApplicationUserPermission.ADMIN.getPermissons()))) {
            throw new IllegalAccessException("You do not have permission to delete this project.");
        }

        projectRepository.deleteProjectById(id);

        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

    public ProjectWithImageNameResponseSo updateProject(ProjectUpdateSo projectInput, Long id, Boolean isDraft, MultipartFile file) throws Exception {
        UserDetails ud = getUserDetails();
        UserEntity currentUser = userRepository.findUserByUsernameAndDeletedIsFalse(ud.getUsername()).get();

        ProjectEntity project = projectRepository.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );
        if(!currentUser.getOwnedProjects().contains(project) && !ud.getAuthorities().contains(new SimpleGrantedAuthority(ApplicationUserPermission.ADMIN.getPermissons()))) {
            throw new IllegalAccessException("You do not have permission to update this project.");
        }

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
        project = projectRepository.save(project);
        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

    private void imageTypeCheck(MultipartFile file){
        if(!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("Only files can be uploaded.");
        }
    }

    private UserDetails getUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
