package entus.resourceServer.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDto {
    List<BoardListDto> boards;
    List<PedalListDto> pedals;

    public HomeDto(List<BoardListDto> boardListDtoList, List<PedalListDto> pedalListDtoList) {
        this.boards = boardListDtoList;
        this.pedals = pedalListDtoList;
    }
}
