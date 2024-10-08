package nl.gelton.steunideebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;

    @OneToOne
    private UserAvatar userAvatar;

    @JsonIgnore
    private String password;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Idea> ideas;


    public User(String username, String email, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }


//    public User(String username, String email) {
//        this.username = username;
//        this.email = email;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        return userId != null && userId.equals(((User) o).getUserId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}


