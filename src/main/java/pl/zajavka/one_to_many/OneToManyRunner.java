package pl.zajavka.one_to_many;

import pl.zajavka.HibernateUtil;
import pl.zajavka.one_to_many.HQL.OwnerRepositoryHQL;

import java.util.Set;

public class OneToManyRunner {
    public static void main(String[] args) {
        OwnerRepository ownerRepository = new OwnerRepository();
        ownerRepository.deleteAll();

        OwnerRepositoryHQL ownerRepositoryHQL = new OwnerRepositoryHQL();
        ownerRepositoryHQL.saveTestData();

        ownerRepositoryHQL.selectExamples1();
        ownerRepositoryHQL.selectExamples2();
        ownerRepositoryHQL.selectExamples3_1("romek@zajavka.pl");
        ownerRepositoryHQL.selectExamples3_2();
        ownerRepositoryHQL.selectExamples4();
        ownerRepositoryHQL.selectExamples5_1();
        ownerRepositoryHQL.selectExamples5_2();
        ownerRepositoryHQL.selectExamples5_3();
        ownerRepositoryHQL.selectExamples5_4();
        ownerRepositoryHQL.selectExamples6();
        ownerRepositoryHQL.selectExamples7_1();
        ownerRepositoryHQL.selectExamples7_2();
        ownerRepositoryHQL.selectExamples7_3();
        ownerRepositoryHQL.selectExamples7_4();
        ownerRepositoryHQL.selectExamples7_5();
        ownerRepositoryHQL.selectExamples8();
        ownerRepositoryHQL.selectExamples9_1();
        ownerRepositoryHQL.selectExamples9_2();

        HibernateUtil.closeSessionFactory();
    }
}


//
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));
//
//        System.out.println("###Owner1: " + ownerRepository.getOwner(owner1.getId()));
//        System.out.println("###Owner2: " + ownerRepository.getOwner(owner2.getId()));
//
//        Pet newPet = Pet.builder().name("Drapek").breed(Breed.MONKEY).owner(owner1).build();
//        ownerRepository.updateOwner(owner1.getId(), newPet);
//        System.out.println("###Owner updated: " + ownerRepository.getOwner(owner1.getId()));
////
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));
////
////        ownerRepository.deleteOwner(owner2.getId());
////
//        ownerRepository.listOwners()
//                .forEach(owner -> System.out.println("###Owner listing: " + owner));
