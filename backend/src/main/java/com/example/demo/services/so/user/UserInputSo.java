package com.example.demo.services.so.user;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "UserInput")
public class UserInputSo extends User{
    private String password;

    private String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
