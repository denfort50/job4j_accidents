package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.hibernate.RuleHibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RuleService {

    private final RuleHibernate ruleHibernate;
    private final List<Rule> ruleList;

    public RuleService(RuleHibernate ruleHibernate) {
        this.ruleHibernate = ruleHibernate;
        this.ruleList = getRulesFromDB();
    }

    public List<Rule> getRulesFromDB() {
        return ruleHibernate.getRulesFromDB();
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return Arrays.stream(ids).map(id -> ruleList.get(Integer.parseInt(id) - 1)).collect(Collectors.toSet());
    }

}
