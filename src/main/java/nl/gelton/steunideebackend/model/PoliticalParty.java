package nl.gelton.steunideebackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "political_parties")
public class PoliticalParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;
    private String description;

    @OneToOne(mappedBy = "politicalParty")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="logo")
    private ProfileImage logo;

    @ManyToMany(mappedBy = "politicalPartyLikes")
    private Set<Idea> topIdeas = new HashSet<>();

    private final LocalDateTime createdAt = LocalDateTime.now();

}
