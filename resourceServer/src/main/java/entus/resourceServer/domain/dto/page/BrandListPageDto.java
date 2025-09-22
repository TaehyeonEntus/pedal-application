package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BrandListDto;
import lombok.Data;

import java.util.List;

@Data
public class BrandListPageDto {
    private int totalPage;
    private int currentPage;
    private List<BrandListDto> pedals;

    public BrandListPageDto(int totalPage, int currentPage, List<BrandListDto> pedals) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pedals = pedals;
    }
}
