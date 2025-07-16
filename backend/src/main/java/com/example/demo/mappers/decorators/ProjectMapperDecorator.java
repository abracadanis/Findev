package com.example.demo.mappers.decorators;

import com.example.demo.model.ProjectEntity;
import com.example.demo.mappers.ProjectMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.so.project.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.NoSuchElementException;

public abstract class ProjectMapperDecorator implements ProjectMapper {
    @Autowired
    private UserRepository userRepository;

    private ProjectMapper delegate;

    @Autowired
    public void setDelegate(ProjectMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ProjectEntity mapToEntity(Project input) throws NoSuchElementException {
        ProjectEntity entity = new ProjectEntity();

        entity.setTitle(input.getTitle());
        entity.setDescription(input.getDescription());

        return entity;
    }



}
