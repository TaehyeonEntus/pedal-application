package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Board;
import lombok.Data;

@Data
public class BoardListDto {
    private Long boardId;
    private String boardOwner;
    private String boardName;
    private String boardImageUrl;

    public BoardListDto(Board board) {
        this.boardId = board.getId();
        this.boardOwner = board.getUser().getName();
        this.boardName = board.getName();
        this.boardImageUrl = board.getImageUrl();
    }
}
