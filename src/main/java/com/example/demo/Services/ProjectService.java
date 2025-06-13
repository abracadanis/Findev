package com.example.demo.Services;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.project.ProjectWithFullImageResponseSo;
import com.example.demo.Services.so.project.ProjectWithImageNameResponseSo;
import com.example.demo.Services.so.project.ProjectInputSo;
import com.example.demo.Services.so.project.ProjectSo;
import com.example.demo.Services.so.user.UserSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProjectService{

    private static final String PROJECT_IMAGES_PATH = "src/main/resources/projectimages";

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
        if(userRepo.findUserById(projectInputSo.getOwnerId()).isPresent()) {

            ProjectEntity projectEntity = projectMapper.mapToEntity(projectInputSo);
            UserEntity userEntity = userRepo.findUserById(projectInputSo.getOwnerId()).get();
            projectEntity.getUsers().add(userEntity);
            projectEntity.setDraft(isDraft);

            if(!file.isEmpty()) {
                String fileName = imageService.saveFileToFileSystem(file);
                projectEntity.setImageFileName(fileName);
            }

            projectEntity = projectRepo.save(projectEntity);

            userEntity.getProjects().add(projectRepo.findProjectById(projectEntity.getId()).get());
            userRepo.save(userEntity);

            return projectMapper.mapToProjectWithImageNameResponse(projectEntity);
        }

        return null;
    }

    public ProjectWithFullImageResponseSo getProjectById(Long id){
        if(projectRepo.findProjectById(id).isPresent()){
            return projectMapper.mapToProjectWithFullImageResponse(projectRepo.findProjectById(id).get());
        }
        return null;
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

    public ProjectWithImageNameResponseSo deleteProject(Long id) {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );
        List<Long> list = getListOfUsers(id);
//        for(int i = 0; i < list.size(); i++){
//            userService.removeProject(id, list.get(i));
//        }
        projectRepo.deleteById(id);

        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

    public ProjectWithImageNameResponseSo updateProject(ProjectInputSo projectInput, Long id, Boolean isDraft) {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(
                () -> new NoSuchElementException(String.format("Project with id '%d' not found", id))
        );
        projectMapper.updateProject(project, projectInput);
        project.setDraft(isDraft);
        project = projectRepo.save(project);
        return projectMapper.mapToProjectWithImageNameResponse(project);
    }

}
