package entus.resourceServer.domain.dto.page;

import entus.resourceServer.domain.dto.response.BoardListDto;
import lombok.Data;

import java.util.List;

@Data
public class BoardListPageDto {
    private int totalPage;
    private int currentPage;
    private List<BoardListDto> boards;

    public BoardListPageDto(int totalPage, int currentPage, List<BoardListDto> boards) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.boards = boards;
    }
}
