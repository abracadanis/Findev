package com.example.demo.Mappers.decorators;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Repos.ImageRepo;
import com.example.demo.Repos.UserRepo;
import com.example.demo.Services.so.project.ProjectInputSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;

public abstract class ProjectMapperDecorator implements ProjectMapper {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageRepo imageRepo;

    private ProjectMapper delegate;

    @Autowired
    public void setDelegate(ProjectMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ProjectEntity mapToEntity(ProjectInputSo input) throws NotFoundException {
        ProjectEntity entity = new ProjectEntity();

        entity.setTitle(input.getTitle());
        entity.setDescription(input.getDescription());

        UserEntity user = userRepo.findUserById(input.getOwnerId()).orElseThrow(
                () -> new NotFoundException(String.format("User with id '%d' not found", input.getOwnerId()))
        );
        entity.setOwner(user);
        if(input.getImageId() != null) {
            entity.setImage(imageRepo.findById(input.getImageId()).orElseThrow(
                    () -> new NotFoundException(String.format("Image with ID '%d' not found", input.getImageId()))
            ));
        }

        return entity;
    }
}
