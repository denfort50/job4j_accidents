package ru.job4j.accident.repository.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {

    private final SessionFactory sessionFactory;

    private static final String GET_ACCIDENT_TYPES = "from AccidentType at order by at.id";

    public List<AccidentType> getAccidentTypesFromDB() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_ACCIDENT_TYPES, AccidentType.class).list();
        }
    }
}
