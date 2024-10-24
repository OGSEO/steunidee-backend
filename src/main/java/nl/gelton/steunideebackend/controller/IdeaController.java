package nl.gelton.steunideebackend.controller;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.LikeRequest;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.IdeaInputDto;
import nl.gelton.steunideebackend.service.interf.IdeaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/idea")
@RequiredArgsConstructor
public class IdeaController {

    private final IdeaService ideaService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> createIdea(@RequestBody IdeaInputDto ideaInputDto) {
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand()
//                .toUri();
        return ResponseEntity.ok(ideaService.createIdea(ideaInputDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllIdeas() {
        return ResponseEntity.ok(ideaService.getAllIdeas());
    }

    @GetMapping("/get-all-from-user")
    public ResponseEntity<Response> getAllIdeasFromUser(@RequestParam Long userId) {
        return ResponseEntity.ok(ideaService.getAllIdeasFromUser(userId));
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

    @PostMapping("/like-idea")
    public ResponseEntity<Response> likeIdea(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(ideaService.likeIdea(likeRequest));
    }

    @PostMapping("/unlike-idea")
    public ResponseEntity<Response> unLikeIdea(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(ideaService.unLikeIdea(likeRequest));
    }



}