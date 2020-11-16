package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.repositories.interfaces.EntryRepository;
import com.javarush.myactivities.services.interfaces.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EntryServiceImpls implements EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryServiceImpls(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public List<Entry> getAll() {
        return StreamSupport.stream(entryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());    }

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

}
