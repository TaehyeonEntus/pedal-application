package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Pedal;
import lombok.Data;

@Data
public class PedalListDto {
    private Long pedalId;
    private String pedalName;
    private String pedalImageUrl;

    public PedalListDto(Pedal pedal) {
        this.pedalId = pedal.getId();
        this.pedalName = pedal.getName();
        this.pedalImageUrl = pedal.getImageUrl();
    }
}
