package com.javarush.myactivities.repositorytests;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ActivityRepositoryTest extends BasicRepositoryTest {

    private static final String NAME = "Test Activity Name";
    private static final String DESC = "Test Activity Description";
    private final Activity ACTIVITY = Activity.builder()
            .name(NAME)
            .description(DESC)
            .build();

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Override
    public void createTest() {
        assertFalse(activityRepository.findAll().iterator().hasNext());
        Project project = createProject();
        activityRepository.save(Activity.builder()
                .name(NAME)
                .description(DESC)
                .project(project)
                .build());
        assertTrue(activityRepository.findAll().iterator().hasNext());
    }

    @Test
    @Override
    public void readTest() {
        final Activity activity = activityRepository.save(ACTIVITY);

        assertEquals(NAME, activity.getName());
        assertEquals(DESC, activity.getDescription());
    }

    @Test
    @Override
    public void updateTest() {
        //insert
        final Long id = activityRepository.save(ACTIVITY).getId();

        // update
        final String newName = "new name";
        final String newDescription = "new description";
        final Activity newActivity = Activity.builder()
                .id(id)
                .name(newName)
                .description(newDescription)
                .build();

        activityRepository.save(newActivity);

        // read and assert update was done
        final Activity activity = activityRepository.findById(id)
                .orElse(new Activity());

        assertEquals(id, activity.getId());
        assertEquals(newName, activity.getName());
        assertEquals(newDescription, activity.getDescription());

    }

    @Test
    @Override
    public void deleteTest() {
        final Long key = activityRepository.save(ACTIVITY).getId();
        activityRepository.deleteById(key);
        final Activity activity = activityRepository.findById(key)
                .orElse(null);
        assertNull(activity);
    }

    private Project createProject() {
        final Project project = Project.builder()
                .name("name")
                .description("descr")
                .build();

        return projectRepository.save(project);
    }
}
