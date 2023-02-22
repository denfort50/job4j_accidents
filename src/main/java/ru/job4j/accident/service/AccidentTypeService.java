package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;

import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;
    private final List<AccidentType> accidentTypeList;

    public AccidentTypeService(AccidentTypeJdbcTemplate accidentTypeJdbcTemplate) {
        this.accidentTypeJdbcTemplate = accidentTypeJdbcTemplate;
        this.accidentTypeList = getAccidentTypesFromDB();
    }

    public final List<AccidentType> getAccidentTypesFromDB() {
        return accidentTypeJdbcTemplate.getAccidentTypesFromDB();
    }

    public AccidentType findById(int id) {
        return accidentTypeList.get(id);
    }

    public final List<AccidentType> getAccidentTypes() {
        return accidentTypeList;
    }
}
