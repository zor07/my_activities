package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
}
