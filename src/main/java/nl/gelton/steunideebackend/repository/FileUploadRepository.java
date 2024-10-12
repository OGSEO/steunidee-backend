package nl.gelton.steunideebackend.repository;

import nl.gelton.steunideebackend.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<ProfileImage, String> {
    Optional<ProfileImage> findByFileName(String fileName);
}
