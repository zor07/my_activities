package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Project;

import java.util.List;

public interface CrudRepository <T, R> {

    List<T> getAll();
    T getById(R id);
    void delete(R id);
    R create(T project);
    void update(T project);

}
