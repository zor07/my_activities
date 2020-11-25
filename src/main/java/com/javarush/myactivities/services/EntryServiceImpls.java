package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.entities.User;
import com.javarush.myactivities.repositories.interfaces.EntryRepository;
import com.javarush.myactivities.services.interfaces.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;

@Service
public class EntryServiceImpls implements EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryServiceImpls(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry getById(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        entryRepository.deleteById(id);
    }

    @Override
    public void save(Entry e) {
        entryRepository.save(e);
    }

    @Override
    public Iterable<Entry> getAllByUserAndDate(User user, LocalDate date) {
        return entryRepository.findAllByUserAndDate(user, date);
    }

    @Override
    public Iterable<Entry> getAllByUser(User user) {
        throw new NotImplementedException();
    }
}
