package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class UpdateBoardInfoRequestDto {
    private String name;
    private String description;
}
