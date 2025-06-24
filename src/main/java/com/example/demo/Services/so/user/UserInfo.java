package com.example.demo.Services.so.user;

import jakarta.validation.constraints.NotNull;

public class UserInfo extends User {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
