package com.example.demo.Services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public class ProjectSo extends Project{

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<Long> users;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isDraft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

}
