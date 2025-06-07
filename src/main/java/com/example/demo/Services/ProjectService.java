package com.example.demo.Services;

import com.example.demo.Entities.ImageEntity;
import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ImageRepo;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.project.ProjectInputSo;
import com.example.demo.Services.so.project.ProjectSo;
import com.example.demo.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService{

    private ProjectRepo projectRepo;

    private UserRepo userRepo;

    private ImageRepo imageRepo;

    private ProjectMapper projectMapper;

    private UserMapper userMapper;

    private UserService userService;

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    public void setImageRepo(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
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

    public ProjectSo createProject(ProjectInputSo projectInputSo, Boolean isDraft) throws NotFoundException {
        if(userRepo.findUserById(projectInputSo.getOwnerId()).isPresent()) {

            ProjectEntity projectEntity = projectMapper.mapToEntity(projectInputSo);
            UserEntity userEntity = userRepo.findUserById(projectInputSo.getOwnerId()).get();
            projectEntity.getUsers().add(userEntity);
            projectEntity.setDraft(isDraft);
            Long projectId = projectRepo.save(projectEntity).getId();

            userEntity.getProjects().add(projectRepo.findProjectById(projectId).get());
            userRepo.save(userEntity);

            return projectMapper.mapToSo(projectEntity);
        }

        return null;
    }

    public ProjectSo getProjectById(Long id){
        if(projectRepo.findProjectById(id).isPresent()){
            return projectMapper.mapToSo(projectRepo.findProjectById(id).get());
        }
        return null;
    }

    public List<ProjectSo> getProjects(Boolean isDraft){
        return projectMapper.mapListToSo(projectRepo.findProjectsByDraft(isDraft));
    }

    public List<UserEntity> getListOfUsers(Long id){
        if(projectRepo.findProjectById(id).isPresent()){
            ProjectEntity project = projectRepo.findProjectById(id).get();
            Set<UserEntity> users = project.getUsers();
            List<UserEntity> userList;
            return userList = new ArrayList<UserEntity>(users);
        } else return null;
    }

    public String saveImageFile(Long projectId, MultipartFile file) throws IOException {
        if(projectRepo.findProjectById(projectId).isPresent()){
            ProjectEntity project = projectRepo.findProjectById(projectId).get();

            ImageEntity imageEntity = new ImageEntity(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes()));
            imageEntity.setProject(project);
            imageRepo.save(imageEntity);

            project.setImage(imageEntity);
            projectRepo.save(project);

            if(project.getImage() != null) {
                return "file uploaded successfully : " + file.getOriginalFilename();
            } else {
                return null;
            }
        } else return "Project doesnt exist";
    }

    public byte[] getImageFile(Long id) throws IOException {
        ImageEntity image = imageRepo.findById(id).get();
        byte[] file = ImageUtils.decompressImage(image.getImage());
        return file;
    }

    public ProjectSo deleteProject(Long id) {
        ProjectEntity project = projectRepo.findProjectById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with id '%d' not found", id))
        );
        List<UserEntity> list = getListOfUsers(id);
        for(int i = 0; i < list.size(); i++){
            userService.removeProject(id, list.get(i).getId());
        }
        projectRepo.deleteById(id);

        return projectMapper.mapToSo(project);
    }

}
