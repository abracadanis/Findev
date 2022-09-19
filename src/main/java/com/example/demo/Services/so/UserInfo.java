package com.example.demo.Services.so;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserInfo {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
