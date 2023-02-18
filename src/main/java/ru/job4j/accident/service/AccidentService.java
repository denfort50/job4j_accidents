package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public Accident create(Accident accident) {

        return accidentMem.create(accident);
    }

    public Accident save(Accident accident) {
        return accidentMem.save(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentMem.getAccidentTypes();
    }

    public List<Rule> getRules() {
        return accidentMem.getRules();
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return accidentMem.getRulesByIds(ids);
    }
}
