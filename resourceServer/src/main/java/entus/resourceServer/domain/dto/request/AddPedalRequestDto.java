package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class AddPedalRequestDto {
    private String name;
    private String description;
    private Long brandId;
    private Long categoryId;
}
