package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.entities.User;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import com.javarush.myactivities.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Iterable<Project> getAllByUser(User user) {
        return projectRepository.findAllByUser(user);
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
