package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_ACCIDENT_TYPES = "select id, name from accident_types";

    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("id"));
        accidentType.setName(resultSet.getString("name"));
        return accidentType;
    };

    public List<AccidentType> getAccidentTypesFromDB() {
        return jdbcTemplate.query(GET_ACCIDENT_TYPES, accidentTypeRowMapper);
    }

}
