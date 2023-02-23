package ru.job4j.accident.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_RULES = "select id, name from rules";
    private static final String CREATE_CONNECTIONS = "insert into accidents_rules (accident_id, rule_id) values (?, ?)";
    private static final String DELETE_CONNECTIONS = "delete from accidents_rules where accident_id = ?";

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    };

    public List<Rule> getRulesFromDB() {
        return jdbcTemplate.query(GET_RULES, ruleRowMapper);
    }

    public void createAccidentsRulesConnections(Accident accident) {
        accident.getRules().forEach(rule -> jdbcTemplate.update(CREATE_CONNECTIONS, accident.getId(), rule.getId()));
    }

    private void deleteAccidentsRulesConnections(Accident accident) {
        jdbcTemplate.update(DELETE_CONNECTIONS, accident.getId());
    }

    public void updateAccidentsRulesConnections(Accident accident) {
        deleteAccidentsRulesConnections(accident);
        createAccidentsRulesConnections(accident);
    }

}
