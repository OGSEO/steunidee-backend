package nl.gelton.steunideebackend.dto.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserInputDto {
    private String username;
    private String password;

}