package com.example.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String surname;

    @ManyToMany
    private List<Project> project;

}
