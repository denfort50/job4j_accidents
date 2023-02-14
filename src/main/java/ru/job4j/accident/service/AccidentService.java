package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public void create(Accident accident) {
        accidentMem.add(accident);
    }

    public void save(Accident accident) {
        accidentMem.save(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }
}
