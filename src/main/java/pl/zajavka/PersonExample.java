package pl.zajavka;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersonExample {

    public static void main(String[] args) {

        try (
                SessionFactory sessionFactory = createSessionFactory();
                Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Robert");
            person.setAge(38);
            session.persist(person);


            session.getTransaction().commit();

            Person person1 = session.find(Person.class, 7);
            System.out.println(person1);
        }
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("/META-INF/hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }
}
