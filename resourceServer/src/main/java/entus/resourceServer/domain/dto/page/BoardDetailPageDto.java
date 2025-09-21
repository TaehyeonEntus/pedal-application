package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BoardDetailDto;
import entus.resourceServer.domain.dto.response.CategoryDto;
import lombok.Data;

import java.util.Set;

@Data
public class BoardDetailPageDto {
    BoardDetailDto boardDetail;
    boolean owner;

    public BoardDetailPageDto(BoardDetailDto boardDetailDto, boolean owner) {
        this.boardDetail = boardDetailDto;
        this.owner = owner;
    }
}
