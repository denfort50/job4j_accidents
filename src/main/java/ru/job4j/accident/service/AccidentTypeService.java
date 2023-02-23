package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.data.AccidentTypeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;
    private final List<AccidentType> accidentTypeList;

    public AccidentTypeService(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
        this.accidentTypeList = getAccidentTypesFromDB();
    }

    public final List<AccidentType> getAccidentTypesFromDB() {
        List<AccidentType> accidentTypes = (List<AccidentType>) accidentTypeRepository.findAll();
        return accidentTypes.stream().sorted(Comparator.comparingInt(AccidentType::getId)).collect(Collectors.toList());
    }

    public AccidentType findById(int id) {
        return accidentTypeList.get(id - 1);
    }

    public final List<AccidentType> getAccidentTypes() {
        return accidentTypeList;
    }
}
