package pl.zajavka.many_to_many;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.util.*;

import static java.util.Objects.isNull;

public class EmployeeRepository {
    List<Employee> insertData(final List<Employee> employees) {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            employees.forEach(employee -> {
                    System.out.println("###BEFORE INSERT\n---------------------------");
                    session.persist(employee);
                    System.out.println("---------------------------\n###AFTER INSERT");
                }
            );


            session.getTransaction().commit();
            return employees;
        }
    }

    List<Employee> listEmployees() {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT employee FROM Employee employee";
            List<Employee> employees = session.createQuery(query, Employee.class).list();

            session.getTransaction().commit();
            return employees;
        }
    }

    Optional<Employee> getEmployee(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            return Optional.ofNullable(session.find(Employee.class, employeeId));
        }
    }

    void updateEmployee(Integer employeeId, Project newProject) {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            Employee employee = session.find(Employee.class, employeeId);
            employee.getProjects().add(newProject);

            session.getTransaction().commit();
        }
    }

    void deleteEmployee(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            System.out.println("###BEFORE DELETE\n---------------------------");

            Employee employee = session.find(Employee.class, employeeId);
            employee.getProjects().clear();

            // przed wykonaniem komendy pod spodem musimy wyczyścić wszyskie Project's nad którymi pracuje
            // nasz Employee dlatego, że w przypadku kiedy mamy ustawiony: CascadeType.ALL
            // hibernate oprócz usunięcia Employee oraz odpowiednich wierszy z: project_assignment
            // usunie nam również Project'y nad którymi pracuje nasz Employee a co za tym idzie jeżeli inny
            // Employee pracował nad tymi projektami, które usuniemy to dostaniemy błąd:
            // Caused by: org.hibernate.exception.ConstraintViolationException: could not execute statement
            //                                  przez
            //Caused by: org.postgresql.util.PSQLException: ERROR:
            // update or delete on table "project" violates foreign key constraint "fk_project_assignment_project"
            // on table "project_assignment"
            session.remove(session.find(Employee.class, employeeId));

            System.out.println("---------------------------\n###AFTER DELETE");
            session.getTransaction().commit();
        }
    }

    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "select employee from Employee employee";
            session.createQuery(query, Employee.class).list().forEach(session::remove);
            // tutaj nam to zadzniała ponieważ usuwamy wszystkich Employee czyli też wszystkie projekty
            // nie będziemy mieli takiej sytuacji, jaka jest opisana powyżej

            session.getTransaction().commit();
        }
    }
}
