package com.example.demo.Services.so.project;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProjectWithFullImageResponseSo extends ProjectSo{
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private byte[] imageByteArray;

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }
}
