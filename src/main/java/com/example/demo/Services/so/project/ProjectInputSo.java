package com.example.demo.Services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

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
