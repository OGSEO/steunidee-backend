package nl.gelton.steunideebackend.config;

import nl.gelton.steunideebackend.model.AppRole;
import nl.gelton.steunideebackend.model.Role;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.RoleRepository;
import nl.gelton.steunideebackend.repository.UserRepository;
import nl.gelton.steunideebackend.security.CustomUserDetailsService;
import nl.gelton.steunideebackend.security.jwt.AuthEntryPointJwt;
import nl.gelton.steunideebackend.security.jwt.AuthTokenFilter;
import nl.gelton.steunideebackend.security.jwt.JwtUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    public SecurityConfig(AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
//                .csrf(csrf ->
//                        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                                .ignoringRequestMatchers("/**"))
//                .ignoringRequestMatchers("/auth/public/**"))
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/**").permitAll()
//                                .requestMatchers("/admin/**").permitAll()
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/csrf-token").permitAll()
                                .anyRequest().permitAll())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(unauthorizedHandler))
                .addFilterBefore(authenticationJwtTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_USER)));


            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_ADMIN)));


            if (!userRepository.existsByUsername("user1")) {
                User user1 = new User("user1", "user1@example.com",  passwordEncoder.encode("password1"));
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setRole(userRole);
                userRepository.save(user1);
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", "admin@example.com", passwordEncoder.encode("adminPass"));
                admin.setAccountNonLocked(true);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setRole(adminRole);
                userRepository.save(admin);
            }
        };
    }
}

