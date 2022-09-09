package com.example.demo.Services;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService{

    private ProjectRepo projectRepo;

    private UserRepo userRepo;

    private ProjectMapper projectMapper;

    private UserMapper userMapper;

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

}
