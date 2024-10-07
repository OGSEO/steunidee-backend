package nl.gelton.steunideebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LoginUserOutputDto {
    private String jwtToken;
    private String username;
    private List<String> roles;

    public LoginUserOutputDto(String username, List<String> roles, String jwtToken) {
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

}

