package com.example.demo.Services;

import com.example.demo.Entities.Project;
import com.example.demo.Mappers.ProjectMapper;
import com.example.demo.Repos.ProjectRepo;

import com.example.demo.Services.so.ProjectInputSo;
import com.example.demo.Services.so.ProjectSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService{

    private ProjectRepo projectRepo;

    private ProjectMapper projectMapper;

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public ProjectSo saveProject(ProjectInputSo projectInputSo) {
        Project project = projectMapper.mapToEntity(projectInputSo);
        project.setId(123L);
        projectRepo.save(project);
        return projectMapper.mapToSo(project);
    }

}
