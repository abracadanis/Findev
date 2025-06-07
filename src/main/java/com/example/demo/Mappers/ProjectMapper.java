package com.example.demo.Mappers;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Mappers.decorators.ProjectMapperDecorator;
import com.example.demo.Services.so.project.ProjectInputSo;
import com.example.demo.Services.so.project.ProjectSo;
import org.mapstruct.*;

import java.util.List;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
        //uses = UserMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
@DecoratedWith(ProjectMapperDecorator.class)
public interface ProjectMapper {

    ProjectEntity copy(ProjectEntity projectEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "owner.id", source = "ownerId"),
            @Mapping(target = "image.id", source = "imageId")
    })
    ProjectEntity mapToEntity(ProjectInputSo projectInputSo);

    @Mappings({
            @Mapping(target = "imageId", source = "image.id"),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "users", source = "users", qualifiedByName = "usersToIdList")
    })
    ProjectSo mapToSo(ProjectEntity projectEntity);

    @Named("usersToIdList")
    public static Long usersToIdList(UserEntity user) {
        return user.getId();
    }

    List<ProjectSo> mapListToSo(List<ProjectEntity> projectEntities);

}
