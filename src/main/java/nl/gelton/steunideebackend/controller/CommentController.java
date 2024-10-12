package nl.gelton.steunideebackend.controller;

import lombok.RequiredArgsConstructor;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.service.interf.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> createComment(@RequestBody CommentInputDto commentInputDto) {
        return ResponseEntity.ok(commentService.createComment(commentInputDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllIdeas() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PutMapping("/update/{commentId}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> updateComment(@PathVariable Long commentId,
                                                         @RequestBody CommentInputDto commentInputDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentInputDto));
    }

    @DeleteMapping("/delete/{commentId}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Response> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @GetMapping("/get-comment-by-id/{commentId}")
    public ResponseEntity<Response> getCommentById(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }
}
