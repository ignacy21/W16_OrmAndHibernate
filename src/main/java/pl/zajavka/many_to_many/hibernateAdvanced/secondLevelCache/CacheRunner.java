package pl.zajavka.many_to_many.hibernateAdvanced.secondLevelCache;

import pl.zajavka.HibernateUtil;
import pl.zajavka.many_to_many.hibernateAdvanced.hikariCP.HikariCPExamples;

public class CacheRunner {
    public static void main(String[] args) {
        CachedEmployeeRepository cachedEmployeeRepository = new CachedEmployeeRepository();

        cachedEmployeeRepository.deleteAll();

        cachedEmployeeRepository.insertData(ExampleData.someEmployee1());
        CashedEmployee cashedEmployee = cachedEmployeeRepository.insertData(ExampleData.someEmployee2());
        cachedEmployeeRepository.insertData(ExampleData.someEmployee3());


        cachedEmployeeRepository.getEmployeeWithStats(cashedEmployee.getEmployeeId());
        cachedEmployeeRepository.getEmployeeWithStats(cashedEmployee.getEmployeeId());

        HibernateUtil.closeSessionFactory();
    }


}
