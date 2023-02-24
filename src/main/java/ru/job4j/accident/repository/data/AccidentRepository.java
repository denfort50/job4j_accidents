package ru.job4j.accident.repository.data;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    List<Accident> findAll();
}
