package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
}
