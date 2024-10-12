package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.AddressInputDto;

public interface AddressService {

    Response saveAndUpdateAddress(AddressInputDto addressInputDto);
}
