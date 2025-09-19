package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class PresignedUrlResponseDto {
    private String uploadUrl;
    private String objectKey;

    public PresignedUrlResponseDto(String uploadUrl, String objectKey) {
        this.uploadUrl = uploadUrl;
        this.objectKey = objectKey;
    }
}
