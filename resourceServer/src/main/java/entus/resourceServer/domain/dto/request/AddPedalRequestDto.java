package entus.resourceServer.domain.dto.request;

import entus.resourceServer.domain.Category;
import lombok.Data;

@Data
public class AddPedalRequestDto {
    private String name;
    private String description;
    private Category category;
    private String imageUrl;
}
