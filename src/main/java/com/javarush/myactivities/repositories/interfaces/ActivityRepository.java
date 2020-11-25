package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Iterable<Activity> findAllByUser(User user);
}
