package nl.gelton.steunideebackend.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import nl.gelton.steunideebackend.model.Idea;
import nl.gelton.steunideebackend.model.User;

@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@AllArgsConstructor
//@NoArgsConstructor
public class CommentInputDto {

    private String content;
    private String name;
    private Long ideaId;

}
