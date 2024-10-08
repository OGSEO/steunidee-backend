package nl.gelton.steunideebackend.controller;

import jakarta.validation.Valid;
import nl.gelton.steunideebackend.dto.LoginUserOutputDto;
import nl.gelton.steunideebackend.dto.MessageResponse;
import nl.gelton.steunideebackend.dto.input.LoginUserInputDto;
import nl.gelton.steunideebackend.dto.input.RegisterUserInputDto;
import nl.gelton.steunideebackend.model.AppRole;
import nl.gelton.steunideebackend.model.Role;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.RoleRepository;
import nl.gelton.steunideebackend.repository.UserRepository;
import nl.gelton.steunideebackend.security.jwt.JwtUtils;
//import nl.gelton.steunideebackend.service.UserService;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
//    private final UserService userService;

    public AuthController(JwtUtils jwtUtils,
                          AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder
//                          UserService userService
    ) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
//        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserInputDto registerUser) {
        if (userRepository.existsByUsername(registerUser.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerUser.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User newUser = new User(
                registerUser.getUsername(),
                registerUser.getEmail(),
                passwordEncoder.encode(registerUser.getPassword())
        );

        Set<String> strRoles = registerUser.getRole();

        Role role;

        // Set default Role USER
        if (strRoles == null || strRoles.isEmpty()) {
            role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            String roleStr = strRoles.iterator().next();
            if (roleStr.equals("admin")) {
                role = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            } else {
                role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
        }

        newUser.setAccountNonLocked(true);
        newUser.setAccountNonExpired(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        newUser.setRole(role);
        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserInputDto loginUser) {

        Authentication authentication;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        // Set the authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Specific to our implemetation
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        // Collect roles from the UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Prepare the response body, now including the JWT token directly in the body
        LoginUserOutputDto response = new LoginUserOutputDto(userDetails.getUsername(),
                roles, jwtToken);

        // Return the response entity with the JWT token included in the response body
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/user")
//    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.findByUsername(userDetails.getUsername());
//
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//
//        UserInfoRespponse response = new UserInfoRespponse(
//                user.getUserId(),
//                user.getFirstname(),
//                user.getLastname(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getAvatarImage(),
//                user.isAccountNonLocked(),
//                user.isAccountNonExpired(),
//                user.isCredentialsNonExpired(),
//                user.isEnabled(),
//                user.getCredentialsExpiryDate(),
//                user.getAccountExpiryDate(),
//                user.isTwoFactorEnabled(),
//                roles
//        );
//
//
//        return ResponseEntity.ok().body(response);
//    }

//
//    @PostMapping("/user/avatar")
//    public ResponseEntity<User> addAvatarToUser(@AuthenticationPrincipal UserDetails userDetails,
//                                                @RequestBody MultipartFile avatarFile) throws IOException {
//        String username = userDetails.getUsername();
//
//
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/auth")
//                .path(Objects.requireNonNull(username))
//                .path("/avatar")
//                .toUriString();
//
//
//        AvatarImage avatarImage = avatarService.storeFile(avatarFile, url);
//
//
//        User user = userService.addAvatar(username, avatarImage);
//
//
//        return ResponseEntity.created(URI.create(url)).body(user);
//    }
//
//
//    @GetMapping("/user/avatar")
//    public ResponseEntity<byte[]> getUserAvatar(@AuthenticationPrincipal UserDetails userDetails) {
//
//
//        AvatarImage avatarImage = userService.getAvatarFromUser(userDetails.getUsername());
//
//
//        MediaType mediaType;
//
//
//        try {
//            mediaType = MediaType.parseMediaType(avatarImage.getContentType());
//        } catch (InvalidMediaTypeException ignore) {
//            mediaType = MediaType.APPLICATION_OCTET_STREAM;
//        }
//
//
//        return ResponseEntity.ok().contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + avatarImage.getTitle())
//                .body(avatarImage.getImage());
//    }
//
//
//    @GetMapping("/username")
//    public String currentUsername(@AuthenticationPrincipal UserDetails userDetails) {
//        return (userDetails != null) ? userDetails.getUsername() : "";
//    }
}






