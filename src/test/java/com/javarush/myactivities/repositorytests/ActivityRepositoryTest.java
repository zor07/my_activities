package com.javarush.myactivities.repositorytests;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        assertEquals(0, activityRepository.getAll().size());
        Long projectId = createProject();
        activityRepository.create(Activity.builder()
                .name(NAME)
                .description(DESC)
                .project(Project.builder().id(projectId).build())
                .build());
        assertEquals(1, activityRepository.getAll().size());
    }

    @Test
    @Override
    public void readTest() {
        final Long key = activityRepository.create(ACTIVITY);
        final Activity activity = activityRepository.getById(key);

        assertEquals(key, activity.getId());
        assertEquals(NAME, activity.getName());
        assertEquals(DESC, activity.getDescription());
    }

    @Test
    @Override
    public void updateTest() {
        //insert
        final Long key = activityRepository.create(ACTIVITY);

        // update
        final String newName = "new name";
        final String newDescription = "new description";
        final Activity newActivity = Activity.builder()
                .id(key)
                .name(newName)
                .description(newDescription)
                .build();
        activityRepository.update(newActivity);

        // read and assert update was done
        final Activity activity = activityRepository.getById(key);
        assertEquals(key, activity.getId());
        assertEquals(newName, activity.getName());
        assertEquals(newDescription, activity.getDescription());

    }

    @Test
    @Override
    public void deleteTest() {
        final Long key = activityRepository.create(ACTIVITY);
        activityRepository.delete(key);
        final Activity activity = activityRepository.getById(key);
        assertNull(activity);
    }

    private Long createProject() {
        final Project project = Project.builder()
                .name("name")
                .description("descr")
                .build();

        return projectRepository.create(project);
    }
}
