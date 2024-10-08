package nl.gelton.steunideebackend.service;

import jakarta.transaction.Transactional;
import nl.gelton.steunideebackend.exception.RecordNotFoundException;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.model.UserAvatar;
import nl.gelton.steunideebackend.repository.FileUploadRepository;
import nl.gelton.steunideebackend.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileUploadRepository fileUploadRepository;
    private final AvatarService avatarService;

    public UserService(UserRepository userRepository, FileUploadRepository fileUploadRepository, AvatarService avatarService) {
        this.userRepository = userRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.avatarService = avatarService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("User with id: " + id + " not found");
        }
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("User with username: " + username + " not found");
        }
    }

    @Transactional
    public Resource getAvatarFromUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("User with id " + userId + " not found.");
        }
        UserAvatar avatar = optionalUser.get().getUserAvatar();
        if(avatar == null){
            throw new RecordNotFoundException("User " + userId + " had no avatar.");
        }
        return avatarService.downLoadFile(avatar.getFileName());
    }

    @Transactional
    public User assignAvatarToUser(String filename, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<UserAvatar> optionalAvatar = fileUploadRepository.findByFileName(filename);

        if (optionalUser.isPresent() && optionalAvatar.isPresent()) {
            UserAvatar avatar = optionalAvatar.get();
            User user = optionalUser.get();
            user.setUserAvatar(avatar);
            return userRepository.save(user);
        } else {
            throw new RecordNotFoundException("user of avatar niet gevonden");
        }
    }
}

