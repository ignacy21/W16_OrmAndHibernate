package pl.zajavka.namedQueryExample;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@NamedQueries({
        @NamedQuery(
                name = "Person.findAll",
                query = "FROM Person"),
        @NamedQuery(
                name = "Person.findPersonByName",
                query = "FROM Person WHERE name = :name"
        )
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "person_id")
        private Long id;

        @Column(name = "name")
        private String name;
}
