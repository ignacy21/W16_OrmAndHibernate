package pl.zajavka.one_to_many;

import jakarta.persistence.*;
import lombok.*;
import pl.zajavka.one_to_many.HQL.Toy;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "breed")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "pet_toy",
            joinColumns = {@JoinColumn(name = "pet_id")},
            inverseJoinColumns = {@JoinColumn(name = "toy_id")}
    )
    private Set<Toy> toys;

    @Override
    public String toString() {
        return "Pet(id=" + this.getId() + ", name=" + this.getName()
                + ", breed=" + this.getBreed() + ")";
    }
}
