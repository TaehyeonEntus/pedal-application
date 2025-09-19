package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class AddBoardResponseDto {
    private Long boardId;

    public AddBoardResponseDto(Long boardId) {
        this.boardId = boardId;
    }
}
