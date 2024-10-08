package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<UserAvatar, String> {
    Optional<UserAvatar> findByFileName(String fileName);
}
