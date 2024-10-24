package nl.gelton.steunideebackend;

import nl.gelton.steunideebackend.enums.UserRole;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SteunIdeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteunIdeeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
            ) {
        return args -> {
            var user = User.builder()
                    .name("Ollie Gelton")
                    .email("o@g.nl")
                    .password(passwordEncoder.encode("qwerty"))
                    .role(UserRole.CITIZEN)
                    .build();
            userRepository.save(user);
        };
    }
}
