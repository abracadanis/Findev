package com.example.demo.services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "ProjectInput")
public class ProjectInputSo extends Project{
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Positive
    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
