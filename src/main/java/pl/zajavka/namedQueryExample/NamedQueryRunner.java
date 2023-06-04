package pl.zajavka.namedQueryExample;

import pl.zajavka.HibernateUtil;

public class NamedQueryRunner {

    public static void main(String[] args) {
        NamedQueryData namedQueryData = new NamedQueryData();
        namedQueryData.deleteAllPerson();

        namedQueryData.insertPerson("Karol");
        namedQueryData.insertPerson("Krzysiek");
        namedQueryData.insertPerson("Maciek");
        namedQueryData.insertPerson("Zbyszek");
        namedQueryData.insertPerson("Zuzia");

        namedQueryData.printAll();
        namedQueryData.namedQueryExample();
        namedQueryData.printAll();

        HibernateUtil.closeSessionFactory();
    }


}
