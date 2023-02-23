package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.hibernate.AccidentTypeHibernate;

import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeHibernate accidentTypeHibernate;
    private final List<AccidentType> accidentTypeList;

    public AccidentTypeService(AccidentTypeHibernate accidentTypeHibernate) {
        this.accidentTypeHibernate = accidentTypeHibernate;
        this.accidentTypeList = getAccidentTypesFromDB();
    }

    public final List<AccidentType> getAccidentTypesFromDB() {
        return accidentTypeHibernate.getAccidentTypesFromDB();
    }

    public AccidentType findById(int id) {
        return accidentTypeList.get(id - 1);
    }

    public final List<AccidentType> getAccidentTypes() {
        return accidentTypeList;
    }
}
