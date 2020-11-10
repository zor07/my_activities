package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
