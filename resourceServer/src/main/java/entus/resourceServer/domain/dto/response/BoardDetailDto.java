package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.BoardPedal;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Data
public class BoardDetailDto {
    private Long boardId;
    private String boardOwner;
    private String boardName;
    private String boardDescription;
    private String boardImageUrl;
    private LocalDateTime createdAt;
    private List<PedalListDto> pedals;

    public BoardDetailDto(Board board) {
        this.boardId = board.getId();
        this.boardOwner = board.getUser().getName();
        this.boardName = board.getName();
        this.boardDescription = board.getDescription();
        this.boardImageUrl = board.getImageUrl();
        this.createdAt = board.getCreatedAt();
        this.pedals = board.getPedals()
                .stream()
                .sorted(Comparator.comparingInt(BoardPedal::getOrderIndex))
                .map((BoardPedal pedal) -> new PedalListDto(pedal.getPedal()))
                .toList();
    }
}
