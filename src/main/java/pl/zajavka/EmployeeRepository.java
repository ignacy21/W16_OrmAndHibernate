package pl.zajavka;

import ch.qos.logback.core.model.conditional.ElseModel;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeRepository {

    // Commands below are crucial, without them there won't be any inserts
    // session.beginTransaction();
    // session.getTransaction().commit();

    Employee insert(final Employee employee) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
            return employee;
        }
    }

    public void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            String query = "SELECT emp from Employee emp";
            session.createQuery(query, Employee.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }

    List<Employee> listEmployees() {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            String query = "SELECT emp from Employee emp";
            List<Employee> employeeList = session.createQuery(query, Employee.class).list();
            session.getTransaction().commit();
            return employeeList;
        }
    }

    void updateEmployee(final Integer employeeId, BigDecimal newSalary) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            Employee employee = session.find(Employee.class, employeeId);
            employee.setSalary(newSalary);
            session.getTransaction().commit();
        }
    }

    void deleteEmployee(final Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            session.remove(session.find(Employee.class, employeeId));
            session.getTransaction().commit();
        }
    }


    Optional<Employee> getEmployee(final Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            Objects.requireNonNull(session);
            session.beginTransaction();
            Optional<Employee> employee = Optional.ofNullable(session.find(Employee.class, employeeId));
            session.getTransaction().commit();
            return employee;
        }
    }
}
