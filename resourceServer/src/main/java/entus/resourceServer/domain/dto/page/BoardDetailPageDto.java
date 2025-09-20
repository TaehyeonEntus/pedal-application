package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BoardDetailDto;
import lombok.Data;

@Data
public class BoardDetailPageDto {
    BoardDetailDto boardDetail;
    boolean isOwner;

    public BoardDetailPageDto(BoardDetailDto boardDetailDto, boolean isOwner) {
        this.boardDetail = boardDetailDto;
        this.isOwner = isOwner;
    }
}
