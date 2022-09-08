package com.example.demo.Mappers;

import com.example.demo.Entities.Project;
import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import org.mapstruct.*;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
)
public interface ProjectMapper {

    Project copy(Project project);

    Project mapToEntity(ProjectInputSo projectInputSo);

    ProjectSo mapToSo(Project project);

}
