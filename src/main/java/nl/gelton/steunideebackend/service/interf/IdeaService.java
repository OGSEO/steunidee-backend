package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.dto.input.PoliticalPartyInputDto;

public interface IdeaService {

    Response getAllIdeas();

    Response getIdeaById(Long ideaId);

    Response createIdea(IdeaInputDto ideaInputDto);

    Response updateIdea(Long ideaId, IdeaInputDto ideaInputDto);

    Response deleteIdea(Long ideaId);
}
