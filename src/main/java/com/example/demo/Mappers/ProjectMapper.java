package com.example.demo.Mappers;

import com.example.demo.Entities.ProjectEntity;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
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

    @Mappings({
            @Mapping(target = "imageId", source = "image.id")
    })
    ProjectSo mapToSo(ProjectEntity projectEntity);

    List<ProjectSo> mapListToSo(List<ProjectEntity> projectEntities);

}
