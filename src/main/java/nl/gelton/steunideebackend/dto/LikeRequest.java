package nl.gelton.steunideebackend.dto;

import lombok.Data;

@Data
public class LikeRequest {

    private Long ideaId;
    private Long userId;
}
