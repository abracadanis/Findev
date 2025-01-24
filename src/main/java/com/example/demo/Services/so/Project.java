package com.example.demo.Services.so;

import com.example.demo.Entities.ImageEntity;
import com.example.demo.Entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class Project {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Schema(nullable = true)
    private Set<UserEntity> userEntities;

    @Schema(nullable = true)
    private Long imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getUsers() {
        return userEntities;
    }

    public void setUsers(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
