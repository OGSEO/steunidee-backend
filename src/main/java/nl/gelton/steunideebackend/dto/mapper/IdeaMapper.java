package nl.gelton.steunideebackend.dto.mapper;

import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.dto.output.CommentOutputDto;
import nl.gelton.steunideebackend.dto.output.IdeaOutputDto;
import nl.gelton.steunideebackend.model.Comment;
import nl.gelton.steunideebackend.model.Idea;

public class IdeaMapper {

    public static Idea fromInputDtoToModel(IdeaInputDto ideaInputDto) {
        Idea idea = new Idea();
        idea.setTitle(ideaInputDto.getTitle());
        idea.setDescription(ideaInputDto.getDescription());
        return idea;
    }

    public static IdeaOutputDto fromModelToOutputDto(Idea idea) {
        IdeaOutputDto ideaOutputDto = new IdeaOutputDto();
        ideaOutputDto.setId(idea.getId());
        ideaOutputDto.setTitle(idea.getTitle());
        ideaOutputDto.setDescription(idea.getDescription());
        return ideaOutputDto;
    }
}
