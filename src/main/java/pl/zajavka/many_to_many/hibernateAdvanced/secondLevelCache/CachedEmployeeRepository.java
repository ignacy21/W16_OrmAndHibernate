package pl.zajavka.many_to_many.hibernateAdvanced.secondLevelCache;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import org.hibernate.stat.Statistics;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

public class CachedEmployeeRepository {

    public CashedEmployee insertData(CashedEmployee employee) {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
            return employee;
        }
    }
//    List<CashedEmployee> listEmployees() {
//        try (Session session = HibernateUtil.getSession()) {
//            if (isNull(session)) {
//                throw new RuntimeException("Session is null");
//            }
//            session.beginTransaction();
//
//            String query = "SELECT employee FROM Employee employee";
//            List<CashedEmployee> employees = session.createQuery(query, CashedEmployee.class).list();
//
//            session.getTransaction().commit();
//            return employees;
//        }
//    }
//
//    Optional<CashedEmployee> getEmployee(Integer employeeId) {
//        try (Session session = HibernateUtil.getSession()) {
//            if (isNull(session)) {
//                throw new RuntimeException("Session is null");
//            }
//            return Optional.ofNullable(session.find(CashedEmployee.class, employeeId));
//        }
//    }
//
//    void updateEmployee(Integer employeeId, Project newProject) {
//        try (Session session = HibernateUtil.getSession()) {
//            if (isNull(session)) {
//                throw new RuntimeException("Session is null");
//            }
//            session.beginTransaction();
//
//            CashedEmployee employee = session.find(CashedEmployee.class, employeeId);
//            employee.getProjects().add(newProject);
//
//            session.getTransaction().commit();
//        }
//    }
//
//    void deleteEmployee(Integer employeeId) {
//        try (Session session = HibernateUtil.getSession()) {
//            if (isNull(session)) {
//                throw new RuntimeException("Session is null");
//            }
//            session.beginTransaction();
//
//            System.out.println("###BEFORE DELETE\n---------------------------");
//
//            CashedEmployee employee = session.find(CashedEmployee.class, employeeId);
//            employee.getProjects().clear();
//
//            session.remove(session.find(CashedEmployee.class, employeeId));
//
//            System.out.println("---------------------------\n###AFTER DELETE");
//            session.getTransaction().commit();
//        }
//    }

    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "select employee from CashedEmployee employee";
            session.createQuery(query, CashedEmployee.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }


    Optional<CashedEmployee> getEmployeeWithStats(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CashedEmployee employee = session.find(CashedEmployee.class, employeeId);
            System.out.printf("###Employee: %s %s%n", employee.getName(), employee.getSurname());
            stats(HibernateUtil.getStatistics());

            session.getTransaction().commit();
            return Optional.of(employee);
        }
    }

    public static void stats(Statistics statistics) {
        System.out.println("Misses in second level cache:" + statistics.getSecondLevelCacheMissCount());
        System.out.println("Added to second level cache:" + statistics.getSecondLevelCachePutCount());
        System.out.println("Found in second level cache:" + statistics.getSecondLevelCacheHitCount());
        statistics.clear();
    }
}
