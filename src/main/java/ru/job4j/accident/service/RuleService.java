package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleJdbcTemplate ruleJdbcTemplate;

    public List<Rule> getRules() {
        return ruleJdbcTemplate.getRules();
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return ruleJdbcTemplate.getRulesByIds(ids);
    }

    public Set<Rule> getRulesByAccidentId(int id) {
        return ruleJdbcTemplate.getRulesByAccidentId(id);
    }

    public void createAccidentsRulesConnections(Accident accident) {
        ruleJdbcTemplate.createAccidentsRulesConnections(accident);
    }

    public void updateAccidentsRulesConnections(Accident accident) {
        ruleJdbcTemplate.updateAccidentsRulesConnections(accident);
    }
}
