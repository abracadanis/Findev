package com.example.demo.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    @ManyToMany
    private List<User> users;
}
