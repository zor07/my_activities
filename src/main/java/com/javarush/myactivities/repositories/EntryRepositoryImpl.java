package com.javarush.myactivities.repositories;

import com.javarush.myactivities.entities.Activity;
import com.javarush.myactivities.entities.Entry;
import com.javarush.myactivities.entities.Project;
import com.javarush.myactivities.repositories.interfaces.EntryRepository;
import com.javarush.myactivities.utils.DateUtils;
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
public class EntryRepositoryImpl implements EntryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryRepositoryImpl.class);

    private final JdbcTemplate jdbc;

    private final RowMapper<Entry> entryRowMapper = (rs, rowNum) -> Entry.builder()
            .id(rs.getLong("entry.id"))
            .comment(rs.getString("entry.comment"))
            .date(DateUtils.date2LocalDate(rs.getDate("entry.date")))
            .activity(Activity.builder()
                    .id(rs.getLong("activity.id"))
                    .name(rs.getString("activity.name"))
                    .description(rs.getString("activity.description"))
                    .project(Project.builder()
                            .id(rs.getLong("project.id"))
                            .name(rs.getString("project.name"))
                            .description(rs.getString("project.description"))
                            .build())
                    .build())
            .build();

    @Autowired
    public EntryRepositoryImpl(@Qualifier("jdbc") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Entry> getAll() {
        @Language("MySQL")
        final String query = "select * from entry\n " +
                "join activity on entry.activity_id = activity.id\n" +
                "left join project on activity.project_id = project.id";

        return jdbc.query(query, entryRowMapper);
    }

    @Override
    public Entry getById(Long id) {
        @Language("MySQL")
        final String query = "select * from entry\n " +
                "join activity on entry.activity_id = activity.id\n" +
                "left join project on activity.project_id = project.id\n" +
                "where entry.id = ?;";

        try {
            return jdbc.queryForObject(query, new Object[]{id}, entryRowMapper);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Couldn't find entry with id={}", id, e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        @Language("MySQL")
        final String query = "delete from entry where id=?";
        jdbc.update(query, id);
    }

    @Override
    public Long create(Entry entry) {
        @Language("MySQL")
        final String query = "insert into entry (activity_id, date, comment) " +
                "values (?, ?, ?);";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con -> {
            final PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entry.getActivity().getId());
            ps.setDate(2, DateUtils.localDate2Date(entry.getDate()));
            ps.setString(3, entry.getComment());
            return ps;
        }, keyHolder);

        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .orElse(null);
    }

    @Override
    public void update(Entry entry) {
        @Language("MySQL")
        final String query = "update entry\n" +
                "set activity_id=?, \n" +
                "    date=?, \n" +
                "    comment=? \n" +
                "where id=?;";

        jdbc.update(query,
                entry.getActivity().getId(),
                entry.getDate(),
                entry.getComment(),
                entry.getId());
    }
}
