package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Board;
import lombok.Data;

@Data
public class BoardDetailDto {
    private BoardInfoDto info;
    private BoardChainDto chain;

    public BoardDetailDto(Board board) {
        this.info = new BoardInfoDto(board);
        this.chain = new BoardChainDto(board);
    }
}
