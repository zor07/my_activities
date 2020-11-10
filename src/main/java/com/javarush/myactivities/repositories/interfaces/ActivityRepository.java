package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
}
