package pl.zajavka;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EmployeeData {

    static Employee someEmployee1() {
        return Employee.builder()
                .name("Agnieszka")
                .surname("Pracownik")
                .salary(new BigDecimal("5910.73"))
                .since(OffsetDateTime.of(2020, 1, 1, 10, 10, 10, 0, ZoneOffset.UTC))
                .build();
    }
    static Employee someEmployee2() {
        return Employee.builder()
                .name("Stefan")
                .surname("Nowacki")
                .salary(new BigDecimal("3455.12"))
                .since(OffsetDateTime.of(2021, 2, 2, 10, 10, 10, 0, ZoneOffset.UTC))
                .build();
    }
    static Employee someEmployee3() {
        return Employee.builder()
                .name("Tomasz")
                .surname("Adamski")
                .salary(new BigDecimal("6112.42"))
                .since(OffsetDateTime.of(2022, 3, 3, 10, 10, 10, 0, ZoneOffset.UTC))
                .build();
    }
}
