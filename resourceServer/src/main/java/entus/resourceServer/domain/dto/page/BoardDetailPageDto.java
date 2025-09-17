package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BoardDetailDto;
import lombok.Data;

@Data
public class BoardDetailPageDto {
    BoardDetailDto boardDetail;

    public BoardDetailPageDto(BoardDetailDto boardDetailDto) {
        this.boardDetail = boardDetailDto;
    }
}
