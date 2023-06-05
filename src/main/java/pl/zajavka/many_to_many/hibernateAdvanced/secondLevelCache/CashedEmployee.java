package pl.zajavka.many_to_many.hibernateAdvanced.secondLevelCache;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.zajavka.many_to_many.Project;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "employeeId")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "cache_employee")
public class CashedEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "since")
    private OffsetDateTime since;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_assignment",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects;

    @Override
    public String toString() {
        return "Employee(employeeId=" + this.getEmployeeId() + ", name=" + this.getName()
                + ", surname=" + this.getSurname() + ", salary=" + this.getSalary()
                + ", since=" + this.getSince() + ", projects=" + this.getProjects() + ")";
    }
}
