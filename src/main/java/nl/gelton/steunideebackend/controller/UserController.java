package nl.gelton.steunideebackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.UserRepository;
import nl.gelton.steunideebackend.service.AvatarService;
import nl.gelton.steunideebackend.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AvatarService avatarService;

    public UserController(UserService userService, AvatarService avatarService) {
        this.userService = userService;
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<User> addAvatarToUser(@PathVariable("id") Long userId,
                                                     @RequestBody MultipartFile file)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/")
                .path(Objects.requireNonNull(userId.toString()))
                .path("/avatar")
                .toUriString();
        String fileName = avatarService.storeFile(file);
        User user = userService.assignAvatarToUser(fileName, userId);

        return ResponseEntity.created(URI.create(url)).body(user);
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<Resource> getUserAvatar(@PathVariable("id") Long userId, HttpServletRequest request){
        Resource resource = userService.getAvatarFromUser(userId);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            /*
            "application/octet-steam" is de generieke mime type voor byte data.
            Het is beter om een specifiekere mimetype te gebruiken, zoals "image/jpeg".
            Mimetype is nodig om de frontend te laten weten welke soort data het is.
            Met de juiste MimeType en Content-Disposition, kun je de plaatjes of PDFs die je upload
            zelfs in de browser weergeven.
             */
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}
