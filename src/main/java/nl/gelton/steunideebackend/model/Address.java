package nl.gelton.steunideebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String zipCode;
    private String city;
    private String country;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    private final LocalDateTime createdAt = LocalDateTime.now();


}
