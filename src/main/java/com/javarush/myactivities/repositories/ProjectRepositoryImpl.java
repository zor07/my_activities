package com.javarush.myactivities.repositories;

import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ProjectRepository;
import org.intellij.lang.annotations.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRepositoryImpl.class);

    private final JdbcTemplate jdbc;

    private final RowMapper<Project> projectMapper = (rs, rowNum) ->
            Project.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();

    @Autowired
    public ProjectRepositoryImpl(@Qualifier("jdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Project> getAll() {
        @Language("MySQL")
        final String query = "select * from project;";
        return jdbc.query(query,  projectMapper);
    }

    @Override
    public Project getById(Long id) {
        @Language("MySQL")
        final String query = "select * from project where id=?;";

        try {
            return jdbc.queryForObject(query, new Object[]{id}, projectMapper);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Couldn't find project with id={}", id, e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        @Language("MySQL")
        final String query = "delete from project where id=?;";
        jdbc.update(query, id);
    }

    @Override
    public Long create(Project project) {
        @Language("PostgreSQL")
        final String query = "insert into project (name, description) values (?, ?);";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con -> {
                    PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, project.getName());
                    ps.setString(2, project.getDescription());
                    return ps;
                }, keyHolder);

        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .orElse(null);
    }

    @Override
    public void update(Project project) {
        @Language("MySQL")
        final String query = "update project\n" +
                "set name=?, \n" +
                "    description=?\n" +
                "where id=?;";

        jdbc.update(query, project.getName(), project.getDescription(), project.getId());
    }
}
