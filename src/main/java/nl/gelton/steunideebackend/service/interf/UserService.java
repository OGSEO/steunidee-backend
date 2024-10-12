package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.LoginRequest;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.UserInputDto;
import nl.gelton.steunideebackend.model.User;

public interface UserService {

    Response registerUser(UserInputDto registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getLoggedUser();

    Response getUserInfoWithAddress();
}
