package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.BoardPedal;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class BoardChainDto {
    private List<PedalListDto> pedals;

    public BoardChainDto(Board board) {
        this.pedals = board.getPedals()
                .stream()
                .sorted(Comparator.comparingInt(BoardPedal::getOrderIndex))
                .map((BoardPedal pedal) -> new PedalListDto(pedal.getPedal()))
                .toList();
    }
}
