package pl.zajavka.one_to_many;

public class ExampleData {

    static Owner someOwner1() {
        return Owner.builder().name("Stefan").surname("Nowacki")
                .phone("+48 589 245 114").email("stefan@zajavka.pl").build();
    }
    static Owner someOwner2() {
        return Owner.builder().name("Adrian").surname("Paczkomat")
                .phone("+48 894 256 331").email("adrian@zajavka.pl").build();
    }
    static Pet somePet1() {
        return Pet.builder().name("Fafik").breed(Breed.DOG).build();
    }
    static Pet somePet2() {
        return Pet.builder().name("Kiciek").breed(Breed.CAT).build();
    }
    static Pet somePet3() {
        return Pet.builder().name("Szymek").breed(Breed.MONKEY).build();
    }
    static Pet somePet4() {
        return Pet.builder().name("Gucio").breed(Breed.DOG).build();
    }
}
