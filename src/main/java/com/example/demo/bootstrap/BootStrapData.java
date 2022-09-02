package com.example.demo.bootstrap;

import com.example.demo.Entities.Project;
import com.example.demo.Entities.User;
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
        Project project1 = new Project("Application For You", "Simple mobile application");
        User user1 = new User("Danis", "Gareev");

        project1.getUsers().add(user1);
        user1.getProject().add(project1);
    }
}
