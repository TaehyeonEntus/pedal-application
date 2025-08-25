package entus.resourceServer.domain.dto;

import entus.resourceServer.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListDto {
    private Long boardId;
    private String boardName;
    private String boardDescription;
    private String boardImageUrl;
    private LocalDateTime createdAt;

    public BoardListDto(Board board) {
        this.boardId = board.getId();
        this.boardName = board.getName();
        this.boardDescription = board.getDescription();
        this.boardImageUrl = board.getImageUrl();
        this.createdAt = board.getCreatedAt();
    }
}
