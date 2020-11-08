package com.javarush.myactivities.repositories.interfaces;

import java.util.List;

public interface CrudRepository <T, R> {

    List<T> getAll();
    T getById(R id);
    void delete(R id);
    R create(T entity);
    void update(T entity);

}
