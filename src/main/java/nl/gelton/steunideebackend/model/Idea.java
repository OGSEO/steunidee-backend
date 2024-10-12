package nl.gelton.steunideebackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "ideas")
public class Idea {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
//    @JoinTable(
//            name = "ideas_political_partys",
//            joinColumns = @JoinColumn(name = "idea"),
//            inverseJoinColumns = @JoinColumn(name = "party")
//    )
    private Set<PoliticalParty> politicalPartyLikes = new HashSet<>();

    @ManyToMany
//    @JoinTable(
//        name = "ideas_users",
//            joinColumns = @JoinColumn(name = "idea"),
//            inverseJoinColumns = @JoinColumn(name = "user")
//    )
    private Set<User> userLikes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private final LocalDateTime createdAt = LocalDateTime.now();
}