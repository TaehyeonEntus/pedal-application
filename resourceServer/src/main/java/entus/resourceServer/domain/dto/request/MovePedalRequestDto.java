package entus.resourceServer.domain.dto.request;

import lombok.Data;

@Data
public class MovePedalRequestDto {
    private long pedalId;
    private int destination;
}
