package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final List<AccidentType> accidentTypes = new ArrayList<>(List.of(
            new AccidentType(1, "Две машины"),
            new AccidentType(2, "Машина и человек"),
            new AccidentType(3, "Машина и велосипед")
    ));

    private final AtomicInteger id = new AtomicInteger();

    public Accident create(Accident accident) {
        accident.setId(id.getAndIncrement());
        accident.getType().setName(accidentTypes.get(accident.getType().getId()).getName());
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
        return List.copyOf(accidentTypes);
    }
}
