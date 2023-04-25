package pl.zajavka;

public class CustomerData {

    static Address someAddress1() {
        return Address
                .builder()
                .country("Poland")
                .city("Szczecin")
                .postalCode("70-112")
                .address("Witolda Starkiewicza 3")
                .build();
    }

    static Address someAddress2() {
        return Address
                .builder()
                .country("Poland")
                .city("Gdynie")
                .postalCode("81-357")
                .address("3 Maja 16")
                .build();
    }
    static Customer someCustomer1() {
        return Customer
                .builder()
                .name("Stefan")
                .surname("Siarzewski")
                .phone("+48 690 693 420")
                .email("stefcio123@gmail.com")
                .address(someAddress1())
                .build();
    }
    static Customer someCustomer2() {
        return Customer
                .builder()
                .name("Ferdynand")
                .surname("Lipski")
                .phone("+48 720 112 420")
                .email("fercio123@gmail.com")
                .address(someAddress2())
                .build();
    }
}
