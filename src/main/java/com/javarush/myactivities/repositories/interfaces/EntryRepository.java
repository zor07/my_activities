package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    Iterable<Entry> findAllByUserAndDate(User user, LocalDate date);
}
