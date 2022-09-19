package com.example.demo.Mappers;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Services.so.ProjectInfo;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import com.example.demo.Services.so.UserSo;
import org.mapstruct.*;

import java.util.List;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
)
public interface ProjectMapper {

    ProjectEntity copy(ProjectEntity projectEntity);

    ProjectEntity mapToEntity(ProjectInputSo projectInputSo);

    ProjectInfo mapToInfo(ProjectEntity projectEntity);

    ProjectSo mapToSo(ProjectEntity projectEntity);

    List<ProjectSo> mapListToSo(List<ProjectEntity> projectEntities);

}
