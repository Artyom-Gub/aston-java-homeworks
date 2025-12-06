package www.aston.com.task2.src.main.java.components;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSettings {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("SessionFactory was failed while trying to creation: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}