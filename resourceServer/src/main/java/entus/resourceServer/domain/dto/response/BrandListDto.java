package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Brand;
import lombok.Data;

@Data
public class BrandListDto {
    private Long brandId;
    private String brandName;

    public BrandListDto(Brand brand) {
        this.brandId = brand.getId();
        this.brandName = brand.getName();
    }
}
