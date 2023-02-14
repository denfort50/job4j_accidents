package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentMem accidentMem;

    public Accident add(Accident accident) {
        return accidentMem.add(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }
}
