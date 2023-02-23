package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.data.AccidentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public Accident createOrUpdate(Accident accident, String[] ids) {
        accident.setRules(ruleService.getRulesByIds(ids));
        accident.setType(accidentTypeService.findById(accident.getType().getId()));
        accidentRepository.save(accident);
        return accident;
    }

    public List<Accident> findAll() {
        List<Accident> accidentList = (List<Accident>) accidentRepository.findAll();
        return accidentList.stream().sorted(Comparator.comparingInt(Accident::getId)).collect(Collectors.toList());
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id).orElseThrow();
    }

}
