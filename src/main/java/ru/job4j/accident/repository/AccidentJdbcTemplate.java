package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accidents (name, text, address, accident_type) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
            accident.setId((int) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return accident;
    }

    public Accident update(Accident accident) {
        jdbcTemplate.update(
                "update accidents set name = ?, text = ?, address = ?, accident_type = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(),
                accident.getId());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbcTemplate.query(
                "select id, name, text, address, accident_type from accidents order by id",
                accidentRowMapper);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select id, name, text, address, accident_type from accidents where id = ?",
                accidentRowMapper, id));
    }

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setType(new AccidentType(resultSet.getInt("accident_type")));
        return accident;
    };

}
