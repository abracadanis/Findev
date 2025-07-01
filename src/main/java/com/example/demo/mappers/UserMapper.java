package com.example.demo.mappers;

import com.example.demo.model.ProjectEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.services.so.user.UserInfo;
import com.example.demo.services.so.user.UserInputSo;
import com.example.demo.services.so.user.UserSo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
)
public interface UserMapper {

    UserEntity copy(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "projects", ignore = true),
            @Mapping(target = "ownedProjects", ignore = true)
    })
    UserEntity mapToEntity(UserInputSo userInputSo);

    UserInfo mapToInfo(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "projectsIds", source = "projects", qualifiedByName = "projectsToIdList"),
            @Mapping(target = "ownedProjectsIds", source = "ownedProjects", qualifiedByName = "projectsToIdList")
    })
    UserSo mapToSo(UserEntity userEntity);

    @Named("projectsToIdList")
    static Long projectsToIdList(ProjectEntity project) {
        return project.getId();
    }

    List<UserSo> mapListToSo(List<UserEntity> userEntities);
}
