package com.example.demo.services.so.user;

import java.util.Set;

public class UserSo extends UserInfo {
    private Set<Long> projectsIds;

    private Set<Long> ownedProjectsIds;

    public Set<Long> getProjectsIds() {
        return projectsIds;
    }

    public void setProjectsIds(Set<Long> projectsIds) {
        this.projectsIds = projectsIds;
    }

    public Set<Long> getOwnedProjectsIds() {
        return ownedProjectsIds;
    }

    public void setOwnedProjectsIds(Set<Long> ownedProjectsIds) {
        this.ownedProjectsIds = ownedProjectsIds;
    }
}
