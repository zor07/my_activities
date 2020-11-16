package com.javarush.myactivities.services;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.repositories.interfaces.EntryRepository;
import com.javarush.myactivities.services.interfaces.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<Activity, List<Entry>> getActivityEntriesMap() {
        LocalDate min = LocalDate.now();
        LocalDate max = LocalDate.now();
        final List<Entry> all = getAll();

        for (Entry e : all) {
            if (e.getDate().isBefore(min)) {
                min = e.getDate();
            }

            if (e.getDate().isAfter(max)) {
                max = e.getDate();
            }
        }

        final Map<Activity, List<Entry>> activityEntriesMap = all.stream()
                .collect(Collectors.groupingBy(Entry::getActivity));

        for (Map.Entry<Activity, List<Entry>> mapEntry : activityEntriesMap.entrySet()) {
            for (LocalDate d = min; d.isBefore(max); d = d.plusDays(1)) {
                final LocalDate date = d;
                final boolean hasEntryForThisDate = mapEntry.getValue()
                        .stream()
                        .anyMatch(e -> e.getDate().equals(date));

                if (!hasEntryForThisDate) {
                    Entry e = new Entry();
                    e.setDate(date);
                    e.setActivity(mapEntry.getKey());
                    e.setComment("");
                    mapEntry.getValue().add(e);
                }
            }
        }

        activityEntriesMap.forEach((k, v) -> v.sort(Comparator.comparing(Entry::getDate)));

        return activityEntriesMap;
    }

}
