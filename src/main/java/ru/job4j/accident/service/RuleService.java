package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.data.RuleRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;
    private final List<Rule> ruleList;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
        this.ruleList = getRulesFromDB();
    }

    public List<Rule> getRulesFromDB() {
        List<Rule> rules = (List<Rule>) ruleRepository.findAll();
        return rules.stream().sorted(Comparator.comparingInt(Rule::getId)).collect(Collectors.toList());
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return Arrays.stream(ids).map(id -> ruleList.get(Integer.parseInt(id) - 1)).collect(Collectors.toSet());
    }

}
