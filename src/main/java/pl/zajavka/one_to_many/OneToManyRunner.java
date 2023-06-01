package pl.zajavka.one_to_many;

import pl.zajavka.HibernateUtil;

import java.util.Set;

public class OneToManyRunner {
    public static void main(String[] args) {
        OwnerRepository ownerRepository = new OwnerRepository();

        ownerRepository.deleteAll();

        Owner owner1 = ownerRepository.insertData(ExampleData.someOwner1(),
                Set.of(ExampleData.somePet1(), ExampleData.somePet2()));
        Owner owner2 = ownerRepository.insertData(ExampleData.someOwner2(),
                Set.of(ExampleData.somePet3(), ExampleData.somePet4()));

        ownerRepository.listOwners()
                .forEach(owner -> System.out.println("###Owner listing: " + owner));

        System.out.println("###Owner1: " + ownerRepository.getOwner(owner1.getId()));
        System.out.println("###Owner2: " + ownerRepository.getOwner(owner2.getId()));

        Pet newPet = Pet.builder().name("Drapek").breed(Breed.MONKEY).owner(owner1).build();
        ownerRepository.updateOwner(owner1.getId(), newPet);
        System.out.println("###Owner updated: " + ownerRepository.getOwner(owner1.getId()));
//
        ownerRepository.listOwners()
                .forEach(owner -> System.out.println("###Owner listing: " + owner));
//
//        ownerRepository.deleteOwner(owner2.getId());
//
        ownerRepository.listOwners()
                .forEach(owner -> System.out.println("###Owner listing: " + owner));

        HibernateUtil.closeSessionFactory();
    }
}
