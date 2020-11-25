package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Iterable<Project> findAllByUser(User user);
}
