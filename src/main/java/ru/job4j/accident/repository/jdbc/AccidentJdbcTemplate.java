package ru.job4j.accident.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.*;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    private static final String CREATE = "insert into accidents (name, text, address, accident_type) values (?, ?, ?, ?)";
    private static final String UPDATE = "update accidents set name = ?, text = ?, address = ?, accident_type = ? where id = ?";
    private static final String FIND_ALL = """
            select a.id, a.name, a.text, a.address, a.accident_type type_id, at.name type_name, ar.rule_id, r.name rule_name from accidents a
            join accident_types at on a.accident_type = at.id
            join accidents_rules ar on a.id = ar.accident_id
            join rules r on ar.rule_id = r.id order by id""";
    private static final String FIND_BY_ID = """
            select a.id, a.name, a.text, a.address, a.accident_type type_id, at.name type_name, ar.rule_id, r.name rule_name from accidents a
            join accident_types at on a.accident_type = at.id
            join accidents_rules ar on a.id = ar.accident_id
            join rules r on ar.rule_id = r.id where a.id = ?""";

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        int accidentId = resultSet.getInt("id");
        accident.setId(accidentId);
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setType(new AccidentType(resultSet.getInt("type_id"), resultSet.getString("type_name")));
        Set<Rule> ruleSet = new HashSet<>();
        while (resultSet.getInt("id") == accidentId) {
            Rule rule = new Rule(resultSet.getInt("rule_id"), resultSet.getString("rule_name"));
            ruleSet.add(rule);
            if (!resultSet.next()) {
                resultSet.previous();
                break;
            }
        }
        accident.setRules(ruleSet);
        if (resultSet.getInt("id") != accidentId) {
            resultSet.previous();
        }
        return accident;
    };

    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
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
        jdbcTemplate.update(UPDATE, accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(), accident.getId());
        return accident;
    }

    public List<Accident> findAll() {
        PreparedStatementCreator psc = connection -> connection.prepareStatement(
                FIND_ALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return jdbcTemplate.query(psc, accidentRowMapper);
    }

    public Optional<Accident> findById(int id) {
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    FIND_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            return ps;
        };
        return Optional.ofNullable(jdbcTemplate.query(psc, accidentRowMapper).get(0));
    }

}
