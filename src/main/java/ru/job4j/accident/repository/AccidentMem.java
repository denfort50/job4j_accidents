package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

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

    private final AtomicInteger id = new AtomicInteger();

    public Accident create(Accident accident) {
        accident.setId(id.getAndIncrement());
        accident.setType(accidentTypes.get(accident.getType().getId()));
        return accidents.put(accident.getId(), accident);
    }

    public Accident save(Accident accident) {
        return accidents.replace(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
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
