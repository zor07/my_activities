package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();
    void delete(Long id);

}
