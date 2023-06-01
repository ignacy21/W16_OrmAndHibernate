package pl.zajavka.one_to_one;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerRepository {

    Customer insertCustomer(final Customer customer) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);

            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    List<Customer> listCustomers() {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            String query = "SELECT cust from Customer cust";
            List<Customer> customerList = session.createQuery(query, Customer.class).list();
            session.getTransaction().commit();
            return customerList;
        }
    }

    Optional<Customer> getCustomer(final Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);

            return Optional.ofNullable(session.find(Customer.class, customerId));
        }
    }

    void updateCustomer(final Integer customerId, Address address) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);

            session.beginTransaction();
            Customer customer = session.find(Customer.class, customerId);
            customer.setAddress(address);
            session.getTransaction().commit();
        }
    }

    void deleteCustomer(final Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);

            session.beginTransaction();
            session.remove(session.find(Customer.class, customerId));
            session.getTransaction().commit();
        }
    }

    public void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);

            session.beginTransaction();
            String query = "SELECT cust from Customer cust";
            session.createQuery(query, Customer.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }

}
