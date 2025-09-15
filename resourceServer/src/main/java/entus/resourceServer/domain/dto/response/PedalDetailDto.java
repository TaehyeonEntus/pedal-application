package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedalDetailDto {
    private Long pedalId;
    private String pedalName;
    private String pedalDescription;
    private Category pedalCategory;
    private String pedalImageUrl;
    private LocalDateTime createdAt;

    public PedalDetailDto(Pedal pedal) {
        this.pedalId = pedal.getId();
        this.pedalName = pedal.getName();
        this.pedalDescription = pedal.getDescription();
        this.pedalCategory = pedal.getCategory();
        this.pedalImageUrl = pedal.getImageUrl();
        this.createdAt = pedal.getCreatedAt();
    }
}
