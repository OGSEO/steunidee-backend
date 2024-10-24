package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.Comment;
import nl.gelton.steunideebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdeaId(Long ideaId);
}
