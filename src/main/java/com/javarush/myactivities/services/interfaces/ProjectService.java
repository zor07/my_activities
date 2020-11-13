package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();
    Project getById(Long id);
    void delete(Long id);
    void save(Project project);

}
