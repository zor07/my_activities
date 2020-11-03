package com.javarush.myactivities.repositories;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ProjectRepositoryImpl(@Qualifier("jdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addProject(Project project) {
        @Language("PostgreSQL")
        final String query = "insert into project (name, description) values (?, ?);";
        jdbc.update(query, project.getName(), project.getDescription());
    }



}
