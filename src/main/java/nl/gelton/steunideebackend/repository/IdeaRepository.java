package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
    List<Idea> findByUserId(Long userId);
}