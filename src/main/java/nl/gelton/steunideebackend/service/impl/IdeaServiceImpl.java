package nl.gelton.steunideebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.dto.mapper.IdeaMapper;
import nl.gelton.steunideebackend.dto.output.IdeaOutputDto;
import nl.gelton.steunideebackend.exception.RecordNotFoundException;
import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.model.User;
import nl.gelton.steunideebackend.repository.IdeaRepository;
import nl.gelton.steunideebackend.service.interf.IdeaService;
import nl.gelton.steunideebackend.service.interf.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserService userService;

    @Override
    public Response getAllIdeas() {
        List<Idea> ideas = ideaRepository.findAll();
        List<IdeaOutputDto> ideaOutputDtos = new ArrayList<>();

        for (Idea idea : ideas) {
            ideaOutputDtos.add(IdeaMapper.fromModelToOutputDto(idea));
        }

        return Response.builder()
                .statusCode(200)
                .statusMessage("Ideas Found Successfully")
                .ideaList(ideaOutputDtos)
                .build();
    }

    @Override
    public Response getIdeaById(Long ideaId) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(()-> new RecordNotFoundException("Idea Not Found"));
        IdeaOutputDto ideaOutputDto = IdeaMapper.fromModelToOutputDto(idea);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Idea Found Successfully")
                .idea(ideaOutputDto)
                .build();
    }

    @Override
    public Response createIdea(IdeaInputDto ideaInputDto) {
        User user = userService.getLoggedUser();
        ideaInputDto.setUser(user);
        Idea idea = ideaRepository.save(IdeaMapper.fromInputDtoToModel(ideaInputDto));
        return Response.builder()
                .statusCode(200)
                .statusMessage("Idea Created Successfully")
                .idea(IdeaMapper.fromModelToOutputDto(idea))
                .build();
    }

    @Override
    public Response updateIdea(Long ideaId, IdeaInputDto ideaInputDto) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(()-> new RecordNotFoundException("Idea Not Found!"));
        idea.setTitle(ideaInputDto.getTitle());
        idea.setDescription(ideaInputDto.getDescription());
        ideaRepository.save(idea);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Idea Updated Successfully")
                .idea(IdeaMapper.fromModelToOutputDto(idea))
                .build();
    }

    @Override
    public Response deleteIdea(Long ideaId) {
        Idea idea = ideaRepository.findById(ideaId).orElseThrow(()-> new RecordNotFoundException("Idea Not Found!"));
        ideaRepository.delete(idea);
        return Response.builder()
                .statusCode(200)
                .statusMessage("Idea Deleted Successfully")
                .build();
    }
}