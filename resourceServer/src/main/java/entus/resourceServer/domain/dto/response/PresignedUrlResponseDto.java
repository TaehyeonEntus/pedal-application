package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class PresignedUrlResponseDto {
    private String uploadUrl;

    public PresignedUrlResponseDto(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
