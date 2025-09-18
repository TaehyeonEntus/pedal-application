package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BoardListDto;
import entus.resourceServer.domain.dto.response.PedalListDto;
import lombok.Data;

import java.util.List;

@Data
public class HomePageDto {
    List<BoardListDto> boards;
    List<PedalListDto> pedals;

    public HomePageDto(List<BoardListDto> boardListDto, List<PedalListDto> pedalListDto) {
        this.boards = boardListDto;
        this.pedals = pedalListDto;
    }
}
