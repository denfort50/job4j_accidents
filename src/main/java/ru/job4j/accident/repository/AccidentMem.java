package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
