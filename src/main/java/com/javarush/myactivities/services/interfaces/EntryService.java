package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.entities.User;

import java.time.LocalDate;

public interface EntryService extends CrudService<Entry, Long> {
    Iterable<Entry> getAllByUserAndDate(User user, LocalDate date);
}
