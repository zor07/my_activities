package com.javarush.myactivities.repositories.interfaces;

import com.javarush.myactivities.entities.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {

    Iterable<Entry> findByDate(LocalDate date);

}
