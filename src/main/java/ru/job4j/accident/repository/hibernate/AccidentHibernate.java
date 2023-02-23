package ru.job4j.accident.repository.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private final SessionFactory sessionFactory;

    private static final String FIND_ALL = "from Accident a order by a.id";
    private static final String FIND_BY_ID = "from Accident a where a.id = :fId";

    public Accident createOrUpdate(Accident accident) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(accident);
            transaction.commit();
            return accident;
        } catch (Exception exception) {
            Transaction transaction = session.getTransaction();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    public List<Accident> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL, Accident.class).list();
        }
    }

    public Optional<Accident> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Accident> query = session.createQuery(FIND_BY_ID, Accident.class);
            query.setParameter("fId", id);
            return query.uniqueResultOptional();
        }
    }
}
