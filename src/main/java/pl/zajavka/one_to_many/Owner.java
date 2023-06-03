package pl.zajavka.one_to_many;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Pet> pets;

    @Override
    public String toString() {
        return "Owner(id=" + this.getId() + ", name=" + this.getName()
                + ", surname=" + this.getSurname() + ", phone=" + this.getPhone()
                + ", email=" + this.getEmail() + ", pets=" + this.getPets() + ")";
    }


}
