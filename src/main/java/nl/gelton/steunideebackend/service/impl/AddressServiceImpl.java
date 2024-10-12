package nl.gelton.steunideebackend.service.impl;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.AddressInputDto;
import nl.gelton.steunideebackend.model.Address;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.AddressRepository;
import nl.gelton.steunideebackend.repository.UserRepository;
import nl.gelton.steunideebackend.service.interf.AddressService;
import nl.gelton.steunideebackend.service.interf.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public Response saveAndUpdateAddress(AddressInputDto addressInputDto) {
        User user = userService.getLoggedUser();
        Address address = user.getAddress();

        if (address == null) {
            address = new Address();
            address.setUser(user);
        }
        if (addressInputDto.getStreet() != null) address.setStreet(addressInputDto.getStreet());
        if (addressInputDto.getZipCode() != null) address.setCity(addressInputDto.getZipCode());
        if (addressInputDto.getCity() != null) address.setCity(addressInputDto.getCity());
        if (addressInputDto.getCountry() != null) address.setCountry(addressInputDto.getCountry());

        addressRepository.save(address);

        String message = (user.getAddress() == null) ? "Address successfully created" : "Address successfully updated";

        return Response.builder()
                .statusCode(200)
                .statusMessage(message)
                .build();

    }
}
