package com.javarush.myactivities;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.ProjectRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    ProjectRepositoryImpl projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Project project = new Project();
        project.setName("PROJECT 1");
        project.setDescription("Project 1 description");

        projectRepository.addProject(project);

    }
}
