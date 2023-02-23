package ru.job4j.accident.repository.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
@AllArgsConstructor
public class RuleHibernate {

    private final SessionFactory sessionFactory;

    private static final String GET_RULES = "from Rule r order by r.id";

    public List<Rule> getRulesFromDB() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_RULES, Rule.class).list();
        }
    }
}
