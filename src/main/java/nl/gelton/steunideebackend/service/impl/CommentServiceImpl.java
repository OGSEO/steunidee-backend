package nl.gelton.steunideebackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.gelton.steunideebackend.dto.Response;
import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.dto.mapper.CommentMapper;
import nl.gelton.steunideebackend.dto.output.CommentOutputDto;
import nl.gelton.steunideebackend.exception.RecordNotFoundException;
import nl.gelton.steunideebackend.model.Comment;
import nl.gelton.steunideebackend.repository.CommentRepository;
import nl.gelton.steunideebackend.service.interf.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Response getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentOutputDto> commentOutputDtos = new ArrayList<>();

        for (Comment comment : comments) {
            commentOutputDtos.add(CommentMapper.fromModelToOutputDto(comment));
        }

        return Response.builder()
                .statusCode(200)
                .statusMessage("Comments Found Successfully")
                .commentList(commentOutputDtos)
                .build();
    }


    @Override
    public Response getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RecordNotFoundException("Comment Not Found"));
        CommentOutputDto commentOutputDto = CommentMapper.fromModelToOutputDto(comment);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Comment Found Successfully")
                .comment(commentOutputDto)
                .build();
    }

    @Override
    public Response createComment(CommentInputDto commentInputDto) {
        Comment comment = commentRepository.save(CommentMapper.fromInputDtoToModel(commentInputDto));
        return Response.builder()
                .statusCode(200)
                .statusMessage("Comment Party Created Successfully")
                .comment(CommentMapper.fromModelToOutputDto(comment))
                .build();
    }

    @Override
    public Response updateComment(Long commentId, CommentInputDto commentInputDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RecordNotFoundException("Comment Not Found!"));
        comment.setContent(commentInputDto.getContent());
        commentRepository.save(comment);

        return Response.builder()
                .statusCode(200)
                .statusMessage("Comment Party Updated Successfully")
                .comment(CommentMapper.fromModelToOutputDto(comment))
                .build();
    }

    @Override
    public Response deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RecordNotFoundException("Comment Not Found!"));
        commentRepository.delete(comment);
        return Response.builder()
                .statusCode(200)
                .statusMessage("Comment Deleted Successfully")
                .build();
    }

}
