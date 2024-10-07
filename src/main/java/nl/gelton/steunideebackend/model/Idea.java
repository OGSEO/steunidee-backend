package nl.gelton.steunideebackend.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Idea {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long ideaId;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}