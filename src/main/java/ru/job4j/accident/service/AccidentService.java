package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentRepository;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public Accident create(Accident accident, String[] ids) {
        accident.setRules(ruleService.getRulesByIds(ids));
        accident.setType(accidentTypeService.findById(accident.getType().getId()));
        accidentRepository.create(accident);
        ruleService.createAccidentsRulesConnections(accident);
        return accident;
    }

    public Accident update(Accident accident, String[] ids) {
        accident.setRules(ruleService.getRulesByIds(ids));
        accident.setType(accidentTypeService.findById(accident.getType().getId()));
        ruleService.updateAccidentsRulesConnections(accident);
        return accidentRepository.update(accident);
    }

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id).orElseThrow();
    }

}
