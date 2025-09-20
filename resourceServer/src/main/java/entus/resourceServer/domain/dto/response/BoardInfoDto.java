package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardInfoDto {
    private Long boardId;
    private String boardOwner;
    private String boardName;
    private String boardDescription;
    private String boardImageUrl;
    private LocalDateTime createdAt;

    public BoardInfoDto(Board board) {
        this.boardId = board.getId();
        this.boardOwner = board.getUser().getName();
        this.boardName = board.getName();
        this.boardDescription = board.getDescription();
        this.boardImageUrl = board.getImageUrl();
        this.createdAt = board.getCreatedAt();
    }
}
