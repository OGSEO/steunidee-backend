package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface CommentService {

    Response getAllComments(Long ideaId);

    Response getCommentById(Long commentId);

    Response createComment(CommentInputDto commentInputDto);

    Response updateComment(Long commentId, CommentInputDto commentInputDto);

    Response deleteComment(Long commentId);
}
