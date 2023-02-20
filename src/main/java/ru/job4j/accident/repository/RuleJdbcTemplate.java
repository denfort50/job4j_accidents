package ru.job4j.accident.repository;

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

    public List<Rule> getRules() {
        return jdbcTemplate.query("select id, name from rules", ruleRowMapper);
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        String idsForSqlRequest = String.join(",", ids);
        String sqlRequest = String.format("select id, name from rules where id in (%s)", idsForSqlRequest);
        return new HashSet<>(jdbcTemplate.query(sqlRequest, ruleRowMapper));
    }

    public Set<Rule> getRulesByAccidentId(int id) {
        return new HashSet<>(jdbcTemplate.query("select r.id, r.name from rules r join accidents_rules ar on r.id = ar.rule_id where ar.accident_id = ?",
                ruleRowMapper, id));
    }

    public void createAccidentsRulesConnections(Accident accident) {
        accident.getRules().forEach(rule ->
                jdbcTemplate.update("insert into accidents_rules (accident_id, rule_id) values (?, ?)",
                        accident.getId(), rule.getId()));
    }

    private void deleteAccidentsRulesConnections(Accident accident) {
        jdbcTemplate.update("delete from accidents_rules where accident_id = ?", accident.getId());
    }

    public void updateAccidentsRulesConnections(Accident accident) {
        deleteAccidentsRulesConnections(accident);
        createAccidentsRulesConnections(accident);
    }

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    };
}
