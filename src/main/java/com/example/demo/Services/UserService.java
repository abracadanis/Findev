package com.example.demo.Services;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ProjectRepo;
import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.user.UserInputSo;
import com.example.demo.Services.so.user.UserSo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService {

    private UserRepo userRepo;

    private ProjectRepo projectRepo;

    private UserMapper userMapper;


    @Autowired
    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public UserSo createUser(UserInputSo userInputSo){
        UserEntity userEntity = userMapper.mapToEntity(userInputSo);
        userRepo.save(userEntity);
        return userMapper.mapToSo(userEntity);
    }

    public UserSo getUserById(Long id){
        if(userRepo.findUserById(id).isPresent()){
            return userMapper.mapToSo(userRepo.findUserById(id).get());
        }
        return null;
    }

    public List<UserSo> getUsers(){
        return userMapper.mapListToSo(userRepo.findAll());
    }

    public UserSo setProject(Long userId, Long projectId){
        UserEntity userEntity = userRepo.findUserById(userId).orElseThrow(() ->
                new NoSuchElementException(String.format("User with ID '%d' not exists", userId))
        );
        ProjectEntity projectEntity = projectRepo.findProjectById(projectId).orElseThrow(() ->
                new NoSuchElementException(String.format("Project with ID '%d' not exists", projectId))
        );
        projectEntity.getUsers().add(userEntity);
        projectRepo.save(projectEntity);
        userEntity = userRepo.findUserById(userId).get();
        return userMapper.mapToSo(userEntity);
    }

    public UserSo removeProject(Long userId, Long projectId){
        UserEntity userEntity;
        ProjectEntity projectEntity;
        UserEntity user = userRepo.findUserById(userId).orElseThrow(() ->
            new NoSuchElementException(String.format("User with ID '%d' not exists", userId))
        );

        ProjectEntity project = projectRepo.findProjectById(projectId).orElseThrow(() ->
            new NoSuchElementException(String.format("Project with ID '%d' not exists", projectId))
        );
        project.getUsers().remove(user);
        user.getProjects().remove(project);
        projectRepo.save(project);
        return userMapper.mapToSo(userRepo.save(user));
    }

    public Long deleteUser(Long id){
        UserEntity userEntity = userRepo.findUserById(id).get();
        Set<ProjectEntity> projects = userEntity.getProjects();
        List<ProjectEntity> projectList = new ArrayList<ProjectEntity>(projects);
        for(int i = 0; i < projectList.size(); i++){
            removeProject(id, projectList.get(i).getId());
        }
        if(userRepo.findUserById(id).isPresent()){
            userRepo.deleteById(id);
            return id;
        } else return null;
    }
}
