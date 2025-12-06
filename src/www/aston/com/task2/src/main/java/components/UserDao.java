package www.aston.com.task2.src.main.java.components;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class UserDao {
    public User create(User user) {
        Transaction transaction = null;

        try (Session session = HibernateSettings.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Create failed", e);
        }
    }

    public Optional<User> findById(Long id) {
        try (Session session = HibernateSettings.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    public List<User> findAll() {
        try (Session session = HibernateSettings.getSessionFactory().openSession()) {
            return session.createQuery("From User", User.class).list();
        }
    }

    public User update(User user) {
        Transaction tx = null;
        try (Session session = HibernateSettings.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            return user;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Update failed", e);
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateSettings.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Delete failed", e);
        }
    }
}