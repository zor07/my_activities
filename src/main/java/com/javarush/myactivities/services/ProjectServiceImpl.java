package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import com.javarush.myactivities.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAll() {
        final List<Project> projects = new ArrayList<>();
        for (Project p : projectRepository.findAll()) {
            projects.add(p);
        }
        return projects;
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
