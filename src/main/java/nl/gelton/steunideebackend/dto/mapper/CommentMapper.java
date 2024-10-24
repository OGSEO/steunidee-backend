package nl.gelton.steunideebackend.dto.mapper;


import nl.gelton.steunideebackend.dto.input.CommentInputDto;
import nl.gelton.steunideebackend.dto.output.CommentOutputDto;
import nl.gelton.steunideebackend.model.Comment;

public class CommentMapper {

    public static Comment fromInputDtoToModel(CommentInputDto commentInputDto) {
        Comment comment = new Comment();
        comment.setContent(commentInputDto.getContent());
        comment.setName(commentInputDto.getName());
        return comment;
    }

    public static CommentOutputDto fromModelToOutputDto(Comment comment) {
        CommentOutputDto commentOutputDto = new CommentOutputDto();
        commentOutputDto.setId(comment.getId());
        commentOutputDto.setContent(comment.getContent());
        commentOutputDto.setCreatedAt(comment.getCreatedAt());
        commentOutputDto.setName(comment.getName());
        commentOutputDto.setIdea(IdeaMapper.fromModelToOutputDto(comment.getIdea()));
        return commentOutputDto;
    }

}
