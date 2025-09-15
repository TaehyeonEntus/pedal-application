package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class SwapPedalRequestDto {
    private long pedal1Id;
    private long pedal2Id;
}
