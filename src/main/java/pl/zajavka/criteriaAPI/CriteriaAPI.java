package pl.zajavka.criteriaAPI;

import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import pl.zajavka.HibernateUtil;
import pl.zajavka.one_to_one.Customer;

import java.util.List;
import java.util.Objects;

public class CriteriaAPI {


    void criteriaExample1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();

            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();
        }
    }

    void criteriaExample2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);

            ParameterExpression<String> parameter1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), parameter1));

            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            query.setParameter(parameter1, "adrian@zajavka.pl");

            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();
        }
    }

    void criteriaSomeOperators() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(

                    criteriaBuilder.and(criteriaBuilder.like(root.get("email"), "%r%"),
                    criteriaBuilder.isNotNull(root.get("phone"))
                    ))
                    .orderBy(criteriaBuilder.desc(root.get("email")));

            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();

//    • isNotNull(); - Create a predicate to test whether the expression is not null.
//    • isNull(); - Create a predicate to test whether the expression is null.
//    • equal(); - Create a predicate for testing the arguments for equality.
//    • notEqual(); - Create a predicate for testing the arguments for inequality.
//    • gt(); - Create a predicate for testing whether the first argument is greater than the second.
//    • ge(); - Create a predicate for testing whether the first argument is greater than or equal to the second.
//    • lt(); - Create a predicate for testing whether the first argument is less than the second.
//    • le(); - Create a predicate for testing whether the first argument is less than or equal to the second.
//    • like(); - Create a predicate for testing whether the expression satisfies the given pattern.
//    • between(); - Create a predicate for testing whether the first argument is between the second and third arguments in.
//    • in(); - Create predicate to test whether given expression is contained in a list of values.
//    • and(); - Create a conjunction of the given boolean expressions.
//    • or(); - Create a disjunction of the given boolean expressions.
        }
    }

    void criteriaSetMaxMinResult() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class); criteriaQuery.select(root);
            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            query.setFirstResult(1);
            query.setMaxResults(2);

            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();
        }
    }
    void criteriaSingleRecord() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            Customer customer = query.getSingleResult();
            System.out.println("###Customer: " + customer);

            session.getTransaction().commit();
        }
    }

    void criteriaDistinct() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.multiselect(root.get("name"));
            criteriaQuery.distinct(true);
            TypedQuery<Tuple> query = session.createQuery(criteriaQuery);
            List<Tuple> result = query.getResultList();
            result.forEach(tuple -> System.out.println("###Tuple: " + tuple.get(0)));


            session.getTransaction().commit();
        }
    }

    void criteriaSort() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root); criteriaQuery.orderBy(
                    criteriaBuilder.asc(root.get("name")), criteriaBuilder.desc(root.get("phone")));

            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();
        }
    }

    void criteriaJoin() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            root.join("address");
            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            session.getTransaction().commit();
        }
    }

    void criteriaAggregateFunctions() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.multiselect( criteriaBuilder.count(root.get("name")), criteriaBuilder.max(root.get("surname")));
            TypedQuery<Tuple> query = session.createQuery(criteriaQuery);

            Tuple result = query.getSingleResult();
            System.out.printf("COUNT: %s, MAX: %s%n", result.get(0), result.get(1));

            session.getTransaction().commit();
        }
    }

    void criteriaUpdate() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Customer> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Customer.class);
            Root<Customer> root = criteriaUpdate.from(Customer.class);
            criteriaUpdate.set("name", "Tomasz");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("email"), "adrian@zajavka.pl"));
            session.createMutationQuery(criteriaUpdate).executeUpdate();

            session.getTransaction().commit();
        }
    }

    void criteriaDelete() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Customer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Customer.class);
            Root<Customer> root = criteriaDelete.from(Customer.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("email"), "adrian@zajavka.pl"));
            session.createMutationQuery(criteriaDelete).executeUpdate();

            session.getTransaction().commit();
        }
    }

}
