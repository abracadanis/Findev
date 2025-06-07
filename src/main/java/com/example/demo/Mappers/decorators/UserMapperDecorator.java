package com.example.demo.Mappers.decorators;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Services.so.user.UserSo;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private ProjectRepo projectRepo;

    public UserSo mapToSo(UserEntity entity){
        UserSo so = new UserSo();
        so.setName(entity.getName());
        so.setSurname(entity.getSurname());

        for(ProjectEntity projects: entity.getProjects()) {

        }
        return so;
    }

}
