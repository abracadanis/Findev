package com.example.demo.services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectWithImageNameResponseSo extends ProjectSo{
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String imageFileName;

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

}
