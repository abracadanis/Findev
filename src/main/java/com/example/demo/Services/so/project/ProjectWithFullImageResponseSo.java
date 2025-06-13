package com.example.demo.Services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectWithFullImageResponseSo extends ProjectSo{
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private byte[] imageFileName;

    public byte[] getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(byte[] imageFileName) {
        this.imageFileName = imageFileName;
    }
}
