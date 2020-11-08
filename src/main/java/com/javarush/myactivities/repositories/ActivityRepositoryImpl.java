package com.javarush.myactivities.repositories;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.ActivityRepository;
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

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl implements ActivityRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityRepositoryImpl.class);

    private final JdbcTemplate jdbc;

    private final RowMapper<Activity> activityRowMapper = (rs, rowNum) -> Activity.builder()
            .id(rs.getLong("a.id"))
            .name(rs.getString("a.name"))
            .description(rs.getString("a.description"))
            .project(Project.builder()
                    .id(rs.getLong("p.id"))
                    .name(rs.getString("p.name"))
                    .description(rs.getString("p.description"))
                    .build())
            .build();



    @Autowired
    public ActivityRepositoryImpl(@Qualifier("jdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Activity> getAll() {
        @Language("MySQL")
        final String query = "select * from activity a\n" +
                "join project p on a.project_id = p.id;";
        return jdbc.query(query, activityRowMapper);
    }

    @Override
    public Activity getById(Long id) {
        @Language("MySQL")
        final String query = "select * from activity a\n" +
                "join project p on a.project_id = p.id\n" +
                "where a.id=?";
        try {
            return jdbc.queryForObject(query, new Object[]{id}, activityRowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Couldn't find activity with id={}", id, e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        @Language("MySQL")
        final String query = "delete from activity where id=?;";
        jdbc.update(query, id);
    }

    @Override
    public Long create(Activity activity) {
        @Language("MySQL")
        final String query = "insert into activity (name, description, project_id) " +
                "values (?, ?, ?);";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, activity.getName());
            ps.setString(2, activity.getDescription());
            ps.setLong(3, activity.getProject().getId());
            return ps;
        }, keyHolder);

        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .orElse(null);
    }

    @Override
    public void update(Activity activity) {
        @Language("MySQL")
        final String query = "update activity\n" +
                "set name = ?,\n" +
                "    description=?,\n" +
                "    project_id=?\n" +
                "where id=?;";

        jdbc.update(query, activity.getName(),
                activity.getDescription(), activity.getProject().getId());
    }
}
