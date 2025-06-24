package com.example.demo.Mappers.decorators;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.project.ProjectInputSo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.NoSuchElementException;

public abstract class ProjectMapperDecorator implements ProjectMapper {
    @Autowired
    private UserRepo userRepo;

    private ProjectMapper delegate;

    @Autowired
    public void setDelegate(ProjectMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ProjectEntity mapToEntity(ProjectInputSo input) throws NoSuchElementException, IOException {
        ProjectEntity entity = new ProjectEntity();

        entity.setTitle(input.getTitle());
        entity.setDescription(input.getDescription());

        return entity;
    }



}
