package nl.gelton.steunideebackend.dto.mapper;

import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.dto.input.UserInputDto;
import nl.gelton.steunideebackend.dto.output.AddressOutputDto;
import nl.gelton.steunideebackend.dto.output.CommentOutputDto;
import nl.gelton.steunideebackend.dto.output.UserOutputDto;
import nl.gelton.steunideebackend.model.Comment;
import nl.gelton.steunideebackend.model.User;

public class UserMapper {
    public static User fromInputDtoToModel(UserInputDto userInputDto) {
        User user = new User();
        user.setName(userInputDto.getName());
        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        user.setRole(userInputDto.getRole());
        return user;
    }

    public static UserOutputDto fromModelToOutputDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(user.getId());
        userOutputDto.setName(user.getName());
        userOutputDto.setEmail(user.getEmail());
        userOutputDto.setRole(user.getRole());
        return userOutputDto;
    }

    public static UserOutputDto fromModelToOutputDtoPlusAddress(User user) {
        UserOutputDto userOutputDto = fromModelToOutputDto(user);
        if (user.getAddress() != null) {
            AddressOutputDto addressOutputDto = AddressMapper.fromModelToOutputDto(user.getAddress());
            userOutputDto.setAddress(addressOutputDto);
        }
        return userOutputDto;
    }

}
