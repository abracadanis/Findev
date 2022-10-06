package com.example.demo.Services;

import com.example.demo.Entities.ImageEntity;
import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ImageRepo;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class ProjectService{

    private ProjectRepo projectRepo;

    private UserRepo userRepo;

    private ImageRepo imageRepo;

    private ProjectMapper projectMapper;

    private UserMapper userMapper;

    private final String FOLDER_PATH = "/home/gareev/git/Findev/src/main/resources/images";

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

    public ProjectSo createProject(ProjectInputSo projectInputSo, Long id) {
        if(userRepo.findUserById(id).isPresent()) {

            ProjectEntity projectEntity = projectMapper.mapToEntity(projectInputSo);
            UserEntity userEntity = userRepo.findUserById(id).get();

            projectEntity.getUsers().add(userEntity);
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

    public List<ProjectSo> getProjects(){
        return projectMapper.mapListToSo(projectRepo.findAll());
    }

    public Long deleteProject(Long id){
        if(projectRepo.findProjectById(id).isPresent()){
            projectRepo.deleteById(id);
            return id;
        } else return null;
    }

    public String saveImageFile(Long projectId, MultipartFile file) {
        if(projectRepo.findProjectById(projectId).isPresent()){
            ProjectEntity project = projectRepo.findProjectById(projectId).get();

            String filePath = FOLDER_PATH + file.getOriginalFilename();
            ImageEntity imageEntity = new ImageEntity(file.getOriginalFilename(), file.getContentType(), filePath);
            imageEntity.setProject(project);
            imageRepo.save(imageEntity);

            project.setImage(imageEntity);
            projectRepo.save(project);

            try{
                file.transferTo(new File(filePath));
            } catch (IOException e){
                e.printStackTrace();
            }


            if(imageEntity != null) {
                return "file uploaded successfully : " + filePath;
            } else {
                return null;
            }
        } else return "Project doesnt exist";
    }

    public byte[] getImageFile(Long id) throws IOException {
        ImageEntity image = projectRepo.findProjectById(id).get().getImage();
        String filePath = image.getFilePath();
        byte[] file = Files.readAllBytes(new File(filePath).toPath());
        return file;
    }

}
