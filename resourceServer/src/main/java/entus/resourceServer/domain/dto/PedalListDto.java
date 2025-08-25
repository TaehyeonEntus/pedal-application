package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Pedal;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedalListDto {
    private Long pedalId;
    private String pedalName;
    private String pedalDescription;
    private String pedalImageUrl;
    private String categoryName;
    private LocalDateTime createdAt;

    public PedalListDto(Pedal pedal) {
        this.pedalId = pedal.getId();
        this.pedalName = pedal.getName();
        this.pedalDescription = pedal.getDescription();
        this.pedalImageUrl = pedal.getImageUrl();
        this.categoryName = pedal.getCategory().getName();
        this.createdAt = pedal.getCreatedAt();
    }
}
