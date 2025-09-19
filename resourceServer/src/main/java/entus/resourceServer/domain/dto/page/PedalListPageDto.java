package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.PedalListDto;
import lombok.Data;

import java.util.List;

@Data
public class PedalListPageDto {
    private int totalPage;
    private int currentPage;
    private List<PedalListDto> pedals;

    public PedalListPageDto(int totalPage, int currentPage, List<PedalListDto> pedals) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pedals = pedals;
    }
}
