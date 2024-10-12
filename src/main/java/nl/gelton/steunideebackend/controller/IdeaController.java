package nl.gelton.steunideebackend.controller;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.service.interf.IdeaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/idea")
@RequiredArgsConstructor
public class IdeaController {

    private final IdeaService ideaService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> createIdea(@RequestBody IdeaInputDto ideaInputDto) {
        return ResponseEntity.ok(ideaService.createIdea(ideaInputDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllIdeas() {
        return ResponseEntity.ok(ideaService.getAllIdeas());
    }

    @PutMapping("/update/{ideaId}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> updateIdea(@PathVariable Long ideaId,
                                                         @RequestBody IdeaInputDto ideaInputDto) {
        return ResponseEntity.ok(ideaService.updateIdea(ideaId, ideaInputDto));
    }

    @DeleteMapping("/delete/{ideaId}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> deleteIdea(@PathVariable Long ideaId) {
        return ResponseEntity.ok(ideaService.deleteIdea(ideaId));
    }

    @GetMapping("/get-idea-by-id/{ideaId}")
    public ResponseEntity<Response> getIdeaById(@PathVariable Long ideaId) {
        return ResponseEntity.ok(ideaService.getIdeaById(ideaId));
    }

}