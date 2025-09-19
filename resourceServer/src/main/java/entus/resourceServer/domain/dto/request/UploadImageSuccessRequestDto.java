package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class UploadImageSuccessRequestDto {
    private String objectKey;

    public UploadImageSuccessRequestDto(String objectKey) {
        this.objectKey = objectKey;
    }
}