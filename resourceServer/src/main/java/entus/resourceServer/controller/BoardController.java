package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.dto.BoardDetailDto;
import entus.resourceServer.domain.dto.DeletePedalDto;
import entus.resourceServer.domain.dto.MovePedalDto;
import entus.resourceServer.domain.dto.SwapPedalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final PedalService pedalService;
    @GetMapping("/board/{boardId}")
    public String board(@PathVariable String boardId) {
        return "boardDetail";
    }

    @GetMapping("/api/board/{boardId}")
    @ResponseBody
    public BoardDetailDto apiBoard(@PathVariable Long boardId) {
        return new BoardDetailDto(boardService.get(boardId));
    }

    @PostMapping("/api/board/{boardId}/delete")
    @ResponseBody
    public BoardDetailDto deletePedal(@PathVariable Long boardId, @RequestBody DeletePedalDto deletePedalDto) {
        Board board = boardService.removePedal(boardId, deletePedalDto.getPedalId());
        return new BoardDetailDto(board);
    }

    @PostMapping("/api/board/{boardId}/move")
    @ResponseBody
    public BoardDetailDto movePedal(@PathVariable Long boardId, @RequestBody MovePedalDto movePedalDto) {
        Board board = boardService.movePedal(boardId, movePedalDto.getPedalId(), movePedalDto.getDestination());
        return new BoardDetailDto(board);
    }

    @PostMapping("/api/board/{boardId}/swap")
    @ResponseBody
    public BoardDetailDto swapPedal(@PathVariable Long boardId, @RequestBody SwapPedalDto swapPedalDto) {
        Board board = boardService.swapPedal(boardId, swapPedalDto.getPedal1Id(), swapPedalDto.getPedal2Id());
        return new BoardDetailDto(board);
    }
}
