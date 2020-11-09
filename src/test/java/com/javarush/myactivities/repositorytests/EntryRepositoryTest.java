package com.javarush.myactivities.repositorytests;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
import com.javarush.myactivities.repositories.interfaces.EntryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryRepositoryTest extends BasicRepositoryTest {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ActivityRepository activityRepository;


    @Test
    @Override
    public void createTest() {
        assertEquals(0, entryRepository.getAll().size());
        entryRepository.create(Entry.builder()
                .activity(createActivity())
                .date(LocalDate.now())
                .comment("Entry comment")
                .build());
        assertEquals(1, entryRepository.getAll().size());
    }


    @Test
    @Override
    public void readTest() {
        final Activity activity = createActivity();
        final Entry entryToSave = Entry.builder()
                .activity(activity)
                .date(LocalDate.now())
                .comment("Entry comment")
                .build();

        final Long key = entryRepository.create(entryToSave);

        final Entry entry = entryRepository.getById(key);
        assertEquals(key, entry.getId());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals("Entry comment", entry.getComment());
        assertEquals(activity.getId(), entry.getActivity().getId());
    }


    @Test
    @Override
    public void updateTest() {
        //insert
        final Activity activity = createActivity();
        final Entry entry = Entry.builder()
                .activity(activity)
                .date(LocalDate.now())
                .comment("Entry comment")
                .build();
        final Long key = entryRepository.create(entry);

        //update
        final Activity newActivity = createActivity();
        final LocalDate newDate = LocalDate.now().minusDays(5);
        final String newEntryComment = "New Entry comment";
        final Entry newEntry = Entry.builder()
                .id(key)
                .activity(newActivity)
                .date(newDate)
                .comment(newEntryComment)
                .build();

        entryRepository.update(newEntry);

        // read and assert update was done
        final Entry fromDb = entryRepository.getById(key);
        assertEquals(key, fromDb.getId());
        assertEquals(newDate, fromDb.getDate());
        assertEquals(newEntryComment, fromDb.getComment());
        assertEquals(newActivity.getId(), fromDb.getActivity().getId());
    }


    @Test
    @Override
    public void deleteTest() {
        final Activity activity = createActivity();
        final Entry entry = Entry.builder()
                .activity(activity)
                .date(LocalDate.now())
                .comment("Entry comment")
                .build();

        final Long key = entryRepository.create(entry);
        entryRepository.delete(key);
        assertNull(entryRepository.getById(key));
    }


    private Activity createActivity() {
        Activity activity = Activity.builder()
                .name("name")
                .description("descr")
                .build();

        final Long id = activityRepository.create(activity);
        activity.setId(id);
        return activity;
    }
}
