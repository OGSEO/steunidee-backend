package nl.gelton.steunideebackend.controller;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
public class ActionController {

    private final LikeService likeService;

    @PostMapping("like-idea")
    public ResponseEntity<Response> likeIdea(@RequestBody Long ideaId) {
        return ResponseEntity.ok(likeService.placeLike(ideaId));
    }
}
