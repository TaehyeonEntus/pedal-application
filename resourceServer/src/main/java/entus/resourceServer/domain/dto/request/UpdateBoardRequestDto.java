package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class UpdateBoardRequestDto {
    private String name;
    private String description;
}
