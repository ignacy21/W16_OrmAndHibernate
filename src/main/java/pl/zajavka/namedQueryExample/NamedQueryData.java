package pl.zajavka.namedQueryExample;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;
import pl.zajavka.one_to_many.Owner;
import pl.zajavka.one_to_many.Pet;

import java.util.Objects;
import java.util.Set;

public class NamedQueryData {

    void namedQueryExample() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("###BEFORE NAMED QUERY\n---------------------------");
            Person person = session.createNamedQuery("Person.findPersonByName", Person.class)
                    .setParameter("name", "Karol")
                    .getSingleResult();
            System.out.println(person);
            System.out.println("---------------------------\n###AFTER NAMED QUERY");

            session.getTransaction().commit();
        }
    }
    void printAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            System.out.println("###BEFORE PRINT ALL\n---------------------------");
            session.createNamedQuery("Person.findAll", Person.class)
                    .getResultList()
                    .forEach(p -> System.out.println("###Entity: " + p));
            System.out.println("---------------------------\n###AFTER PRINT ALL");

            session.getTransaction().commit();
        }
    }

    void insertPerson(final String name) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            Person person = Person.builder().name(name).build();
            session.persist(person);

            session.getTransaction().commit();
        }
    }

    void deleteAllPerson() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "select person from Person person";
            session.createQuery(query, Person.class).list().forEach(session::remove);

            session.getTransaction().commit();
        }
    }
}
