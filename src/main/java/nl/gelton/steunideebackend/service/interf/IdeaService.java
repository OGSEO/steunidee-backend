package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.LikeRequest;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.dto.input.PoliticalPartyInputDto;
import nl.gelton.steunideebackend.model.User;

public interface IdeaService {

    Response getAllIdeas();

    Response getIdeaById(Long ideaId);

    Response createIdea(IdeaInputDto ideaInputDto);

    Response updateIdea(Long ideaId, IdeaInputDto ideaInputDto);

    Response deleteIdea(Long ideaId);

    Response likeIdea(LikeRequest likeRequest);

    Response unLikeIdea(LikeRequest likeRequest);

    Response getAllIdeasFromUser(Long userId);
}
