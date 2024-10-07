package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.AppRole;
import nl.gelton.steunideebackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}

