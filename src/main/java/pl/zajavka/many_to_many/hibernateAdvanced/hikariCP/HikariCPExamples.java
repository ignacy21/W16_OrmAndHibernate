package pl.zajavka.many_to_many.hibernateAdvanced.hikariCP;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;
import pl.zajavka.many_to_many.Employee;

import java.util.Objects;

public class HikariCPExamples {

    public static void example1(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("###BEFORE EXAMPLE 1\n---------------------------");

            Employee e1 = session.find(Employee.class, employeeId);
            System.out.printf("###E1 %s %s%n", e1.getName(), e1.getSurname());

            Employee e2 = session.find(Employee.class, employeeId);
            System.out.printf("###E2 %s %s%n", e2.getName(), e2.getSurname());

            Employee e3 = session.find(Employee.class, employeeId);
            System.out.printf("###E3 %s %s%n", e3.getName(), e3.getSurname());

            System.out.println("---------------------------\n###AFTER EXAMPLE 1");
            session.getTransaction().commit();
        }
    }


    public static void example2(Integer employeeId) {
        System.out.println("###BEFORE EXAMPLE 2\n---------------------------");
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            Employee e1 = session.find(Employee.class, employeeId);
            System.out.printf("###E1 %s %s%n", e1.getName(), e1.getSurname());

            session.getTransaction().commit();
        }

        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            Employee e2 = session.find(Employee.class, employeeId);
            System.out.printf("###E2 %s %s%n", e2.getName(), e2.getSurname());

            session.getTransaction().commit();
        }

        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            Employee e3 = session.find(Employee.class, employeeId);
            System.out.printf("###E3 %s %s%n", e3.getName(), e3.getSurname());

            session.getTransaction().commit();
        }
        System.out.println("---------------------------\n###AFTER EXAMPLE 2");
    }

    public static void example3(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("###BEFORE EXAMPLE 3\n---------------------------");


            Employee e1 = session.find(Employee.class, employeeId);
            System.out.printf("###E1 %s %s%n", e1.getName(), e1.getSurname());

            Employee e2 = session.find(Employee.class, employeeId);
            System.out.printf("###E2 %s %s%n", e2.getName(), e2.getSurname());

            session.evict(e1); // W tym miejscu usuwamy z 1LC tylko encję Employee.

            Employee e3 = session.find(Employee.class, employeeId);
            System.out.printf("###E3 %s %s%n", e3.getName(), e3.getSurname());

            Employee e4 = session.find(Employee.class, employeeId);
            System.out.printf("###E4 %s %s%n", e4.getName(), e4.getSurname());

            session.clear(); // W tym miejscu usuwamy z 1LC wszystkie encje.

            Employee e5 = session.find(Employee.class, employeeId);
            System.out.printf("###E5 %s %s%n", e5.getName(), e5.getSurname());

            Employee e6 = session.find(Employee.class, employeeId);
            System.out.printf("###E6 %s %s%n", e6.getName(), e6.getSurname());

            System.out.println("---------------------------\n###AFTER EXAMPLE 3");
            session.getTransaction().commit();
        }
    }
}

