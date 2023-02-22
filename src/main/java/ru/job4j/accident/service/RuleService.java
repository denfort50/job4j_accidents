package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleService {

    private final RuleJdbcTemplate ruleJdbcTemplate;
    private final List<Rule> ruleList;

    public RuleService(RuleJdbcTemplate ruleJdbcTemplate) {
        this.ruleJdbcTemplate = ruleJdbcTemplate;
        this.ruleList = getRulesFromDB();
    }

    public List<Rule> getRulesFromDB() {
        return ruleJdbcTemplate.getRulesFromDB();
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return Arrays.stream(ids).map(id -> ruleList.get(Integer.parseInt(id))).collect(Collectors.toSet());
    }

    public void createAccidentsRulesConnections(Accident accident) {
        ruleJdbcTemplate.createAccidentsRulesConnections(accident);
    }

    public void updateAccidentsRulesConnections(Accident accident) {
        ruleJdbcTemplate.updateAccidentsRulesConnections(accident);
    }
}
