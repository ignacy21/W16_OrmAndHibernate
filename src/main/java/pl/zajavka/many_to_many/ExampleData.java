package pl.zajavka.many_to_many;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ExampleData {
    static Employee someEmployee1() {
        return Employee.builder().name("Agnieszka").surname("Pracownik")
                .salary(new BigDecimal("5910.73")).since(OffsetDateTime.now()).build();
    }

    static Employee someEmployee2() {
        return Employee.builder().name("Stefan").surname("Nowacki")
                .salary(new BigDecimal("3455.12")).since(OffsetDateTime.now()).build();
    }

    static Employee someEmployee3() {
        return Employee.builder().name("Tomasz").surname("Adamski")
                .salary(new BigDecimal("6112.42")).since(OffsetDateTime.now()).build();
    }

    static Project someProject1() {
        return Project.builder()
                .name("Database migration")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").deadline(OffsetDateTime.of(
                        2027, 10, 3, 12, 0, 0, 0,
                        ZoneOffset.UTC)).build();
    }

    static Project someProject2() {
        return Project.builder()
                .name("Some external system integration")
                .description("Nullam hendrerit tellus nisl, tempus eleifend dui posuere nec.").deadline(OffsetDateTime.of(
                        2025, 10, 2, 12, 0, 0, 0,
                        ZoneOffset.UTC)).build();
    }

    static Project someProject3() {
        return Project.builder()
                .name("Email sending refactoring")
                .description("Nulla maximus ac tellus nec elementum.").deadline(OffsetDateTime.of(
                        2024, 4, 2, 12, 0, 0, 0,
                        ZoneOffset.UTC)).build();

    }
}
