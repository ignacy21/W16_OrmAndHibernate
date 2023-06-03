package pl.zajavka.one_to_many.HQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.hibernate.Session;
import pl.zajavka.HibernateUtil;
import pl.zajavka.one_to_many.*;

import java.util.*;

public class OwnerRepositoryHQL {

    public int insertHQL() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            String hql = ("""
                    INSERT Owner (name, surname, phone, email)
                    VALUES ('Romek', 'Zabawniacha', '+48 658 745 322', 'romek@zajavka.pl') 
                    """);

            Query query = entityManager.createQuery(hql);
            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();

        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }


    public int updateHQL(final String oldEmail, final String newPhone, final String newEmail) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("""
                    UPDATE Owner ow
                    SET ow.phone = :newPhone, ow.email = :newEmail WHERE ow.email = :oldEmail
                    """);
            query.setParameter("oldEmail", oldEmail)
                    .setParameter("newEmail", newEmail).setParameter("newPhone", newPhone);

            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();

        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }

    public int deleteHQL(final String email) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("""
                    DELETE FROM Owner ow WHERE ow.email = :email 
                    """);
            query.setParameter("email", email);
            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();

        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }

    public List<Owner> findAllAndPrint() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT ALL\n---------------------------");
            List<Owner> owners = session.createQuery("FROM Owner", Owner.class)
                    .getResultList();

            owners.forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT ALL");

            session.getTransaction().commit();
            return owners;
        }
    }

    public void selectExamples1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 1\n---------------------------");
            String select1 = "SELECT ow FROM Owner ow";
            session.createQuery(select1, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 1");

            session.getTransaction().commit();
        }
    }

    public void selectExamples2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 2\n---------------------------");
            String select2 = "SELECT ow.id, ow.name FROM Owner ow";
            session.createQuery(select2, Object[].class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + Arrays.asList(entity)));

            // ANOTHER WAY:
//            String select2_v2 = "SELECT new pl.zajavka.OwnerTemp(ow.id, ow.name) FROM Owner ow"; session.createQuery(select2, OwnerTemp.class)
//                    .getResultList()
//                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 2");

            session.getTransaction().commit();
        }
    }

    public void selectExamples3_1(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 3_1\n---------------------------");
            String select3_1 = "SELECT ow FROM Owner ow WHERE ow.email = :email";
            session.createQuery(select3_1, Owner.class)
                    .setParameter("email", email)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 3_1");
            session.getTransaction().commit();
        }
    }

    public void selectExamples3_2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 3_2\n---------------------------");
            String select3_2 = "SELECT ow FROM Owner ow WHERE ow.email LIKE :email";
            session.createQuery(select3_2, Owner.class)
                    .setParameter("email", "romek%")
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 3_2");
            session.getTransaction().commit();
        }
    }

    public void selectExamples4() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 4\n---------------------------");
            String select4 = "SELECT ow FROM Owner ow ORDER BY ow.email ASC, ow.name DESC";
            session.createQuery(select4, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 4");
            session.getTransaction().commit();
        }
    }

    public void selectExamples5_1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 5_1\n---------------------------");
            String select5_1 = "SELECT ow FROM Owner ow ORDER BY ow.email DESC";
            session.createQuery(select5_1, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 5_1");
            session.getTransaction().commit();
        }
    }

    public void selectExamples5_2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 5_2\n---------------------------");
            String select5_2 = "SELECT ow FROM Owner ow ORDER BY ow.email DESC";
            session.createQuery(select5_2, Owner.class)
                    .setFirstResult(0)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 5_2");
            session.getTransaction().commit();
        }
    }

    public void selectExamples5_3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 5_3\n---------------------------");
            String select5_3 = "SELECT ow FROM Owner ow ORDER BY ow.email DESC";
            session.createQuery(select5_3, Owner.class)
                    .setMaxResults(0)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 5_3");
            session.getTransaction().commit();
        }
    }

    public void selectExamples5_4() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 5_4\n---------------------------");
            String select5_4 = "SELECT ow FROM Owner ow ORDER BY ow.email DESC";
            session.createQuery(select5_4, Owner.class)
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 5_4");
            session.getTransaction().commit();
        }
    }

    public void selectExamples6() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 6\n---------------------------");
            String select6 = "SELECT ow FROM Owner ow WHERE ow.name = :name";
            Optional<Owner> result = session.createQuery(select6, Owner.class)
                    .setParameter("name", "Romek")
                    .uniqueResultOptional();
            System.out.println(result);

            System.out.println("---------------------------\n###AFTER SELECT 6");
            session.getTransaction().commit();
        }
    }

    public void selectExamples7_1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();


            System.out.println("###BEFORE SELECT 7_1\n---------------------------");
//            String select7_1 = "SELECT ow FROM Owner ow INNER JOIN ow.pets pt";
            String select7_1 = "SELECT ow FROM Owner ow INNER JOIN FETCH ow.pets pt";

            // warto po JOIN dopisać FETCH - dzięki temu do bazy danych jest generowanych mniej zapytań
            // pozbywamy się w ten sposób problemu n + 1

            session.createQuery(select7_1, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            System.out.println("---------------------------\n###AFTER SELECT 7_1");
            session.getTransaction().commit();
        }
    }

    public void selectExamples7_2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 7_2\n---------------------------");
            String select7_2 = "SELECT ow FROM Owner ow LEFT JOIN FETCH ow.pets pt";
            session.createQuery(select7_2, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 7_2");

            session.getTransaction().commit();
        }
    }

    public void selectExamples7_3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 7_3\n---------------------------");
            String select7_3 = "SELECT ow FROM Owner ow RIGHT JOIN FETCH ow.pets pt";
            session.createQuery(select7_3, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 7_3");

            session.getTransaction().commit();
        }
    }

    public void selectExamples7_4() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 7_4\n---------------------------");
            String select7_4 = "SELECT ow FROM Owner ow FULL JOIN FETCH ow.pets pt";
            session.createQuery(select7_4, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 7_4");

            session.getTransaction().commit();
        }
    }

    public void selectExamples7_5() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 7_5\n---------------------------");
            String select7_5 = "SELECT ow.name, pt.name FROM Owner ow CROSS JOIN Pet pt";
            session.createQuery(select7_5, Object[].class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + Arrays.asList(entity)));
            System.out.println("---------------------------\n###AFTER SELECT 7_5");

            session.getTransaction().commit();
        }
    }

    public void selectExamples8() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 8\n---------------------------");
            String select8 = "SELECT ow FROM Owner ow INNER JOIN FETCH ow.pets pt INNER JOIN FETCH pt.toys ts";
            session.createQuery(select8, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 8");

            session.getTransaction().commit();
        }
    }

    public void selectExamples9_1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 9_1\n---------------------------");
            String select9_1 = "SELECT COUNT(t.toyId) FROM Toy t";
            session.createQuery(select9_1, Long.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 9_1");

            session.getTransaction().commit();
        }
    }

    public void selectExamples9_2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");

            }
            session.beginTransaction();

            System.out.println("###BEFORE SELECT 9_2\n---------------------------");
            String select9_2 = """
                    SELECT new pl.zajavka.one_to_many.HQL.ToyStat(
                                  MAX(t.what),
                                  SUM(t.toyId) / COUNT(t.toyId)
                              ) FROM Toy t
                    """;
            session.createQuery(select9_2, ToyStat.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));
            System.out.println("---------------------------\n###AFTER SELECT 9_2");

            session.getTransaction().commit();
        }
    }

    public void saveTestData() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            // Toy creation and saving
            Toy toy1 = ExampleData.someToy1();
            Toy toy2 = ExampleData.someToy2();
            Toy toy3 = ExampleData.someToy3();
            Toy toy4 = ExampleData.someToy4();
            session.persist(toy1);
            session.persist(toy2);
            session.persist(toy3);
            session.persist(toy4);

            // Tet creation
            Pet pet1 = ExampleData.somePet1();
            Pet pet2 = ExampleData.somePet2();
            Pet pet3 = ExampleData.somePet3();
            Pet pet4 = ExampleData.somePet4();
            pet1.setToys(Set.of(toy1, toy2));
            pet2.setToys(Set.of(toy2, toy3));
            pet3.setToys(Set.of(toy1, toy2, toy3));
            pet4.setToys(Set.of(toy2, toy3, toy4));

            // Owner creation and saving
            Owner owner1 = ExampleData.someOwner1();
            Owner owner2 = ExampleData.someOwner2();
            owner1.setPets(Set.of(pet1, pet2));
            owner2.setPets(Set.of(pet3, pet4));
            owner1.getPets().forEach(pet -> pet.setOwner(owner1));
            owner2.getPets().forEach(pet -> pet.setOwner(owner2));

            session.persist(owner1);
            session.persist(owner2);
            session.getTransaction().commit();
        }
    }

}
