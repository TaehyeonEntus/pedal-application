package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Brand;
import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import lombok.Data;

@Data
public class PedalDetailDto {
    private Long pedalId;
    private String pedalName;
    private String pedalDescription;
    private Category pedalCategory;
    private Brand pedalBrand;
    private String pedalImageUrl;

    public PedalDetailDto(Pedal pedal) {
        this.pedalId = pedal.getId();
        this.pedalName = pedal.getName();
        this.pedalDescription = pedal.getDescription();
        this.pedalCategory = pedal.getCategory();
        this.pedalBrand = pedal.getBrand();
        this.pedalImageUrl = pedal.getImageUrl();
    }
}
