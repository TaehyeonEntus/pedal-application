package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class AddBoardRequestDto {
    private String name;
    private String description;
}