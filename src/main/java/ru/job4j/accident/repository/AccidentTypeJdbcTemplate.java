package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public List<AccidentType> getAccidentTypes() {
        return jdbcTemplate.query("select id, name from accident_types",
                accidentTypeRowMapper);
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select id, name from accident_types where id = ?",
                accidentTypeRowMapper, id));
    }

    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("id"));
        accidentType.setName(resultSet.getString("name"));
        return accidentType;
    };
}
