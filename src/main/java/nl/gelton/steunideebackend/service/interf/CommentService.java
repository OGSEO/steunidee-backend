package nl.gelton.steunideebackend.service.interf;

import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.CommentInputDto;

public interface CommentService {

    Response getAllComments();

    Response getCommentById(Long commentId);

    Response createComment(CommentInputDto commentInputDto);

    Response updateComment(Long commentId, CommentInputDto commentInputDto);

    Response deleteComment(Long commentId);
}
