package nl.gelton.steunideebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.gelton.steunideebackend.enums.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    @JsonIgnore
    @NotBlank(message = "Password is required")
    private String password;

    private UserRole role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_image")
    private ProfileImage profileImage;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "political_party_id")
    private PoliticalParty politicalParty;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Idea> ideas = new ArrayList<>();

    @ManyToMany(mappedBy = "userLikes", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Idea> likedIdeas = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    private final LocalDateTime createdAt = LocalDateTime.now();

}


