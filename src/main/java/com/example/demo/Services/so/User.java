package com.example.demo.Services.so;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class User {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Schema(nullable = true)
    private Set<Project> projects;
}
