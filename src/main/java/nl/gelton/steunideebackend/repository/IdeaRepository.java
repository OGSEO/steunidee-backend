package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
}