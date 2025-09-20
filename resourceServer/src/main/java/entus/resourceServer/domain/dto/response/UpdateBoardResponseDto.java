package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class UpdateBoardResponseDto {
    private String name;
    private String description;
    private String objectKey;

    public UpdateBoardResponseDto(String name, String description, String objectKey) {
        this.name = name;
        this.description = description;
        this.objectKey = objectKey;
    }
}
