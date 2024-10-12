package nl.gelton.steunideebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.PoliticalPartyInputDto;
import nl.gelton.steunideebackend.dto.mapper.PoliticalPartyMapper;
import nl.gelton.steunideebackend.dto.output.PoliticalPartyOutputDto;
import nl.gelton.steunideebackend.exception.RecordNotFoundException;
import nl.gelton.steunideebackend.model.PoliticalParty;
import nl.gelton.steunideebackend.repository.PoliticalPartyRepository;
import nl.gelton.steunideebackend.service.interf.PoliticalPartyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PoliticalPartyImpl implements PoliticalPartyService {

    private final PoliticalPartyRepository politicalPartyRepository;

    @Override
    public Response getAllPoliticalParties() {
        List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
        List<PoliticalPartyOutputDto> politicalPartyOutputDtos = new ArrayList<>();

        for (PoliticalParty politicalParty : politicalParties) {
            politicalPartyOutputDtos.add(PoliticalPartyMapper.fromModelToOutputDto(politicalParty));
        }

        return Response.builder()
                .statusCode(200)
                .statusMessage("Political Parties Found Successfully")
                .partyList(politicalPartyOutputDtos)
                .build();
    }

    @Override
    public Response getPoliticalPartyById(Long politicalPartyId) {
        PoliticalParty politicalParty = politicalPartyRepository.findById(politicalPartyId).orElseThrow(()-> new RecordNotFoundException("Political Party Not Found"));
        PoliticalPartyOutputDto politicalPartyOutputDto = PoliticalPartyMapper.fromModelToOutputDto(politicalParty);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Political Party Found Successfully")
                .party(politicalPartyOutputDto)
                .build();
    }

    @Override
    public Response createPoliticalParty(PoliticalPartyInputDto politicalPartyInputDto) {
        PoliticalParty politicalParty = politicalPartyRepository.save(PoliticalPartyMapper.fromInputDtoToModel(politicalPartyInputDto));
        return Response.builder()
                .statusCode(200)
                .statusMessage("Political Party Created Successfully")
                .party(PoliticalPartyMapper.fromModelToOutputDto(politicalParty))
                .build();
    }

    @Override
    public Response updatePoliticalParty(Long politicalPartyId, PoliticalPartyInputDto politicalPartyInputDto) {
        PoliticalParty politicalParty = politicalPartyRepository.findById(politicalPartyId).orElseThrow(()-> new RecordNotFoundException("Political Party Not Found!"));
        politicalParty.setName(politicalPartyInputDto.getName());
        politicalParty.setDescription(politicalPartyInputDto.getDescription());
        politicalPartyRepository.save(politicalParty);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Political Party Updated Successfully")
                .party(PoliticalPartyMapper.fromModelToOutputDto(politicalParty))
                .build();
    }

    @Override
    public Response deletePoliticalParty(Long politicalPartyId) {
        PoliticalParty politicalParty = politicalPartyRepository.findById(politicalPartyId).orElseThrow(()-> new RecordNotFoundException("Political Party Not Found!"));
        politicalPartyRepository.delete(politicalParty);
        return Response.builder()
                .statusCode(200)
                .statusMessage("Political Party Deleted Successfully")
                .build();
    }
}
