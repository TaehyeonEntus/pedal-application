package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.domain.dto.BoardDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/{boardId}")
    public String board(@PathVariable String boardId) {
        return "boardDetail";
    }

    @GetMapping("/api/board/{boardId}")
    @ResponseBody
    public BoardDetailDto apiBoard(@PathVariable Long boardId) {
        return new BoardDetailDto(boardService.get(boardId));
    }
}
