package pl.zajavka.many_to_many.hibernateAdvanced.secondLevelCache;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ExampleData {

    static CashedEmployee someEmployee1() {
        return CashedEmployee.builder().name("Agnieszka").surname("Pracownik")
                .salary(new BigDecimal("5910.73")).since(OffsetDateTime.now()).build();
    }

    static CashedEmployee someEmployee2() {
        return CashedEmployee.builder().name("Stefan").surname("Nowacki")
                .salary(new BigDecimal("3455.12")).since(OffsetDateTime.now()).build();
    }

    static CashedEmployee someEmployee3() {
        return CashedEmployee.builder().name("Tomasz").surname("Adamski")
                .salary(new BigDecimal("6112.42")).since(OffsetDateTime.now()).build();
    }

}


