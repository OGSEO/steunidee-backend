package nl.gelton.steunideebackend.dto.mapper;

import nl.gelton.steunideebackend.dto.input.AddressInputDto;
import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.dto.output.AddressOutputDto;
import nl.gelton.steunideebackend.dto.output.CommentOutputDto;
import nl.gelton.steunideebackend.model.Address;
import nl.gelton.steunideebackend.model.Comment;

public class AddressMapper {

    public static Address fromInputDtoToModel(AddressInputDto addressInputDto) {
        Address address = new Address();
        address.setStreet(addressInputDto.getStreet());
        address.setZipCode(addressInputDto.getZipCode());
        address.setCity(addressInputDto.getCity());
        address.setCountry(addressInputDto.getCountry());
        return address;
    }

    public static AddressOutputDto fromModelToOutputDto(Address address) {
        AddressOutputDto addressOutputDto = new AddressOutputDto();
        addressOutputDto.setId(address.getId());
        addressOutputDto.setStreet(address.getStreet());
        addressOutputDto.setZipCode(address.getZipCode());
        addressOutputDto.setCity(address.getCity());
        addressOutputDto.setCountry(address.getCountry());
        return addressOutputDto;
    }

}
