package com.example.demo.Services.so.user;

import jakarta.validation.constraints.NotBlank;

public class User {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
