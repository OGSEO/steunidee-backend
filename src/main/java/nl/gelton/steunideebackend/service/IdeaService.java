package nl.gelton.steunideebackend.service;

import nl.gelton.steunideebackend.exception.RecordNotFoundException;
import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.repository.IdeaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    public Idea createIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public Idea getIdeaById(Long ideaId) {
        Optional<Idea> idea = ideaRepository.findById(ideaId);
        if (idea.isPresent()) {
            return idea.get();
        } else {
            throw new RecordNotFoundException("not found idea with id " + ideaId);
        }
    }

    public Idea updateIdea(Long ideaId, Idea updatedIdea) {
        Optional<Idea> idea = ideaRepository.findById(ideaId);
        if (idea.isPresent()) {
            Idea i = idea.get();
            i.setTitle(updatedIdea.getTitle());
            i.setDescription(updatedIdea.getDescription());
            return ideaRepository.save(i);
        } else {
            throw new RecordNotFoundException("Idea not foud " + ideaId);
        }
    }
}