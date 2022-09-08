package com.example.demo.bootstrap;


import com.example.demo.Repos.ProjectRepo;
import com.example.demo.Repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public BootStrapData(ProjectRepo projectRepo, UserRepo userRepo){
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception{

    }
}
