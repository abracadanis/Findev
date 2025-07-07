package com.example.demo.mappers;

import com.example.demo.model.ProjectEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.mappers.decorators.ProjectMapperDecorator;
import com.example.demo.services.so.project.*;
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
            @Mapping(target = "id", ignore = true)
    })
    ProjectEntity mapToEntity(Project project) throws IOException;

    @Mappings({
            @Mapping(target = "imageFileName", source = "imageFileName"),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "users", source = "users", qualifiedByName = "usersToIdList")
    })
    ProjectWithImageNameResponseSo mapToProjectWithImageNameResponse(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "imageByteArray", ignore = true),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "users", source = "users", qualifiedByName = "usersToIdList")
    })
    ProjectWithFullImageResponseSo mapToProjectWithFullImageResponse(ProjectEntity projectEntity);

    @Named("usersToIdList")
    static Long usersToIdList(UserEntity user) {
        return user.getId();
    }

    List<ProjectWithImageNameResponseSo> mapListToSo(List<ProjectEntity> projectEntities);

    void updateProject(@MappingTarget ProjectEntity projectEntity, ProjectUpdateSo projectInputSo);

}
