package com.example.demo.Mappers;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.decorators.ProjectMapperDecorator;
import com.example.demo.Services.so.project.ProjectWithFullImageResponseSo;
import com.example.demo.Services.so.project.ProjectWithImageNameResponseSo;
import com.example.demo.Services.so.project.ProjectInputSo;
import org.mapstruct.*;

import java.io.IOException;
import java.util.List;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
)
@DecoratedWith(ProjectMapperDecorator.class)
public interface ProjectMapper {

    ProjectEntity copy(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "owner.id", source = "ownerId")
    })
    ProjectEntity mapToEntity(ProjectInputSo project) throws IOException;

    @Mappings({
            @Mapping(target = "imageFileName", source = "imageFileName"),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "users", source = "users", qualifiedByName = "usersToIdList")
    })
    ProjectWithImageNameResponseSo mapToProjectWithImageNameResponse(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "imageFileName", ignore = true),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "users", source = "users", qualifiedByName = "usersToIdList")
    })
    ProjectWithFullImageResponseSo mapToProjectWithFullImageResponse(ProjectEntity projectEntity);

    @Named("usersToIdList")
    static Long usersToIdList(UserEntity user) {
        return user.getId();
    }

    List<ProjectWithImageNameResponseSo> mapListToSo(List<ProjectEntity> projectEntities);

    void updateProject(@MappingTarget ProjectEntity projectEntity, ProjectInputSo projectInputSo);

}
