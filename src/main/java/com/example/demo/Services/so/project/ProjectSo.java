package com.example.demo.Services.so.project;

import java.util.Set;

public class ProjectSo extends ProjectInputSo{

    private Long id;

    private Set<Long> users;

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
