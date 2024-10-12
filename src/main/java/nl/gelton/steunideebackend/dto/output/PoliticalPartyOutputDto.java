package nl.gelton.steunideebackend.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.gelton.steunideebackend.model.ProfileImage;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalPartyOutputDto {

    private Long id;
    private String name;
    private String description;
    private UserOutputDto user;
    private ProfileImage logo;
    private LocalDateTime createdAt;
}
