package com.example.demo.Services.so;

import com.example.demo.Entities.ProjectEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class User {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Schema(nullable = true)
    private Set<ProjectEntity> projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }
}
