package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    public Accident create(Accident accident) {
        jdbcTemplate.update(
                "insert into accidents (name, text, address) values (?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress());
        return accident;
    }

    public Accident update(Accident accident) {
        jdbcTemplate.update(
                "update accidents set name = ?, text = ?, address = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbcTemplate.query(
                "select id, name, text, address from accidents",
                (resultSet, rowNum) -> createAccident(resultSet));
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select id, name, text, address from accidents where id = ?",
                (resultSet, rowNum) -> createAccident(resultSet), id));
    }

    private Accident createAccident(ResultSet resultSet) throws SQLException {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        return accident;
    }

}
