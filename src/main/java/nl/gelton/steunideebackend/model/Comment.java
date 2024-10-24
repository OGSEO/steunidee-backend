package nl.gelton.steunideebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    private String content;
    private String Name;


    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

//    @ManyToOne
//    @JoinColumn(name= "user_id")
//    private User user;

}
