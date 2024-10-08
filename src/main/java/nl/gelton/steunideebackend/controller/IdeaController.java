package nl.gelton.steunideebackend.controller;

import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.service.IdeaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdeas() {
        return ResponseEntity.ok().body(ideaService.getAllIdeas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Idea> getIdeaById(@PathVariable long id) {
        return ResponseEntity.ok().body(ideaService.getIdeaById(id));
    }

    @PostMapping
    public ResponseEntity<Idea> createIdea(@RequestBody Idea idea) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(idea.getIdeaId())
                .toUri();
        return ResponseEntity.created(uri).body(ideaService.createIdea(idea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Idea> updateIdea(@RequestBody Idea idea, @PathVariable long id) {
        Idea i = ideaService.updateIdea(id, idea);
        return ResponseEntity.ok().body(i);
    }
}