package pl.zajavka.one_to_many;

import pl.zajavka.one_to_many.HQL.Toy;

public class ExampleData {

    public static Owner someOwner1() {
        return Owner.builder().name("Stefan").surname("Nowacki")
                .phone("+48 589 245 114").email("stefan@zajavka.pl").build();
    }
    public static Owner someOwner2() {
        return Owner.builder().name("Adrian").surname("Paczkomat")
                .phone("+48 894 256 331").email("adrian@zajavka.pl").build();
    }
    public static Pet somePet1() {
        return Pet.builder().name("Fafik").breed(Breed.DOG).build();
    }
    public static Pet somePet2() {
        return Pet.builder().name("Kiciek").breed(Breed.CAT).build();
    }
    public static Pet somePet3() {
        return Pet.builder().name("Szymek").breed(Breed.MONKEY).build();
    }
    public static Pet somePet4() {
        return Pet.builder().name("Gucio").breed(Breed.DOG).build();
    }
    public static Toy someToy1() {
        return Toy.builder().color("red").what("car").build();
    }
    public static Toy someToy2() {
        return Toy.builder().color("blue").what("flower with thorns").build();
    }
    public static Toy someToy3() {
        return Toy.builder().color("yellow").what("sunglasses").build();
    }
    public static Toy someToy4() {
        return Toy.builder().color("black").what("dog toy").build();
    }
}
