package nl.gelton.steunideebackend.service;

import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.repository.IdeaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
