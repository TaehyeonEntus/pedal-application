package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.PedalDetailDto;
import lombok.Data;

@Data
public class PedalDetailPageDto {
    PedalDetailDto pedalDetail;

    public PedalDetailPageDto(PedalDetailDto pedalDetail) {
        this.pedalDetail = pedalDetail;
    }
}
