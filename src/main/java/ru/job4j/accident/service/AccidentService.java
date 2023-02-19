package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentJdbcTemplate accidentRepository;

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>(Map.of(
            1, new AccidentType(1, "Две машины"),
            2, new AccidentType(2, "Машина и человек"),
            3, new AccidentType(3, "Машина и велосипед")
    ));

    private final Map<Integer, Rule> rules = new HashMap<>(Map.of(
            1, new Rule(1, "Статья. 1"),
            2, new Rule(2, "Статья. 2"),
            3, new Rule(3, "Статья. 3")
    ));

    public Accident create(Accident accident) {
        accident.setType(accidentTypes.get(accident.getType().getId()));
        return accidentRepository.create(accident);
    }

    public Accident update(Accident accident) {
        return accidentRepository.update(accident);
    }

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentTypes.values().stream().toList();
    }

    public List<Rule> getRules() {
        return rules.values().stream().toList();
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return Arrays.stream(ids).map(id -> rules.get(Integer.parseInt(id))).collect(Collectors.toSet());
    }
}
