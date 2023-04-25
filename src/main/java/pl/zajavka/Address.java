package pl.zajavka;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString(exclude = "customer") // important to exclude customer in toString method!!!
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    // !!!!!! Ultimate Version Helps with this !!!!!!
    // mappedBy = "address" <- this parameter has to be name of:
    //      from Customer
    //      @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //      @JoinColumn(name = "address_id", unique = true)
    //      private Address address; <---- this (address)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private Customer customer;


}
