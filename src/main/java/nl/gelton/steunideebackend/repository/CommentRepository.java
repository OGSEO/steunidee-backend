package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
