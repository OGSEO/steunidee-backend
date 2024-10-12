package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.PoliticalPartyInputDto;

public interface PoliticalPartyService {

    Response getAllPoliticalParties();

    Response getPoliticalPartyById(Long politicalPartyId);

    Response createPoliticalParty(PoliticalPartyInputDto politicalPartyInputDto);

    Response updatePoliticalParty(Long politicalPartyId, PoliticalPartyInputDto politicalPartyInputDto);

    Response deletePoliticalParty(Long politicalPartyId);

}
