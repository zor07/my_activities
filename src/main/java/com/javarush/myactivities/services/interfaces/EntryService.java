package com.javarush.myactivities.services.interfaces;

import com.javarush.myactivities.entities.Entry;

import java.time.LocalDate;

public interface EntryService extends CrudService<Entry, Long> {
    Iterable<Entry> getAllByDate(LocalDate date);
}
