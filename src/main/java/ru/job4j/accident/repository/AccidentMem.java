package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger id = new AtomicInteger();

    public Accident create(Accident accident) {
        accident.setId(id.getAndIncrement());
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

}
