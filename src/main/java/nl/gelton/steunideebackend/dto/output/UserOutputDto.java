package nl.gelton.steunideebackend.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.gelton.steunideebackend.enums.UserRole;
import nl.gelton.steunideebackend.model.ProfileImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private AddressOutputDto Address;
    private ProfileImage profileImage;
    private PoliticalPartyOutputDto politicalParty;
    private List<IdeaOutputDto> ideas;
    private Set<IdeaOutputDto> likedIdeas;
    private List<CommentOutputDto> comments;
    private LocalDateTime createdAt;
}
