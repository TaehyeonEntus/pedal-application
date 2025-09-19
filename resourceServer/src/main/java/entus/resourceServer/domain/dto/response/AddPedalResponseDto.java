package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class AddPedalResponseDto {
    private Long pedalId;

    public AddPedalResponseDto(Long pedalId) {
        this.pedalId = pedalId;
    }
}
