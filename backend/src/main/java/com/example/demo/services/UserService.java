package com.example.demo.services;

import com.example.demo.model.ApplicationUserPermission;
import com.example.demo.model.ProjectEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.so.user.UserInputSo;
import com.example.demo.services.so.user.UserSo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository userRepository;

    private ProjectRepository projectRepository;

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepo(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProjectRepo(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserSo createUser(UserInputSo userInputSo){
        try {
            UserEntity userEntity = userMapper.mapToEntity(userInputSo);
            userEntity.setPassword(passwordEncoder.encode(userInputSo.getPassword()));
            userRepository.save(userEntity);
            return userMapper.mapToSo(userEntity);
        } catch (Exception e){
            throw new IllegalArgumentException("User creation failed:" + e.getMessage());
        }

    }

    public UserSo getUserById(Long id){
        if(userRepository.findUserById(id).isPresent()){
            return userMapper.mapToSo(userRepository.findUserById(id).get());
        }
        return null;
    }

    public List<UserSo> getUsers(){
        return userMapper.mapListToSo(userRepository.findAll());
    }

    public UserSo attachUserToProject(Long userId, Long projectId) throws IllegalAccessException {
        UserDetails ud = (UserDetails) getAuthentication().getPrincipal();
        UserEntity currentUser = userRepository.findUserByUsernameAndDeletedIsFalse(ud.getUsername()).get();

        ProjectEntity projectEntity = projectRepository.findProjectById(projectId).orElseThrow(() ->
                new NoSuchElementException(String.format("Project with ID '%d' not exists", projectId))
        );
        UserEntity user = userRepository.findUserById(userId).orElseThrow(() ->
                new NoSuchElementException(String.format("User with ID '%d' not exists", userId))
        );
        if(!currentUser.getOwnedProjects().contains(projectEntity)  && !ud.getAuthorities().contains(new SimpleGrantedAuthority(ApplicationUserPermission.ADMIN.getPermissons()))) {
            throw new IllegalAccessException("You are not allowed to attach users to this project");
        }

        projectEntity.getUsers().add(user);
        projectRepository.save(projectEntity);
        user = userRepository.findUserById(userId).get();
        return userMapper.mapToSo(user);
    }

    public UserSo detachUserFromProject(Long userId, Long projectId) throws IllegalAccessException {
        UserDetails ud = (UserDetails) getAuthentication().getPrincipal();
        UserEntity currentUser = userRepository.findUserByUsernameAndDeletedIsFalse(ud.getUsername()).get();
        ProjectEntity project = projectRepository.findProjectById(projectId).orElseThrow(() ->
            new NoSuchElementException(String.format("Project with ID '%d' not exists", projectId))
        );
        UserEntity user = userRepository.findUserById(projectId).orElseThrow(() ->
                new NoSuchElementException(String.format("Project with ID '%d' not exists", projectId))
        );
        if(!currentUser.getOwnedProjects().contains(project) && !ud.getAuthorities().contains(new SimpleGrantedAuthority(ApplicationUserPermission.ADMIN.getPermissons()))) {
            throw new IllegalAccessException("You are not allowed to detach users from this project");
        }

        project.getUsers().remove(user);
        user.getProjects().remove(project);
        projectRepository.save(project);
        return userMapper.mapToSo(userRepository.save(user));
    }

    @Transactional
    public Long deleteUser(Long id){
        userRepository.findUserById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("User with ID '%d' not exists", id))
        );
        userRepository.deleteUserById(id);
        return id;
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
