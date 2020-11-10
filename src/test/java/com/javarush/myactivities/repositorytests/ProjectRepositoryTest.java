package com.javarush.myactivities.repositorytests;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ProjectRepositoryTest extends BasicRepositoryTest {

    private static final String NAME = "Test Project Name";
    private static final String DESC = "Test Project Description";
    private static final Project PROJECT = Project.builder()
            .name(NAME)
            .description(DESC)
            .build();

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Override
    public void createTest() {
        assertFalse(projectRepository.findAll().iterator().hasNext());
        projectRepository.save(Project.builder().name("asd").build());
        assertTrue(projectRepository.findAll().iterator().hasNext());
    }

    @Test
    @Override
    public void readTest() {
        final Long generatedKey = projectRepository.save(PROJECT).getId();
        final Project project = projectRepository.findById(generatedKey).orElse(new Project());

        assertEquals(generatedKey, project.getId());
        assertEquals(NAME, project.getName());
        assertEquals(DESC, project.getDescription());
    }

    @Test
    @Override
    public void updateTest() {
        // insert
        final Long generatedKey = projectRepository.save(PROJECT).getId();

        // update
        final String newName = "new name";
        final String newDescription = "new description";
        final Project newProject = Project.builder()
                .id(generatedKey)
                .name(newName)
                .description(newDescription)
                .build();
        projectRepository.save(newProject);

        // read and assert update was done
        final Project project = projectRepository.findById(generatedKey).orElse(new Project());
        assertEquals(generatedKey, project.getId());
        assertEquals(newName, project.getName());
        assertEquals(newDescription, project.getDescription());

    }

    @Test
    @Override
    public void deleteTest() {
        final Long generatedKey = projectRepository.save(PROJECT).getId();
        projectRepository.deleteById(generatedKey);
        final Project project = projectRepository.findById(generatedKey)
                .orElse(null);
        assertNull(project);
    }


}
