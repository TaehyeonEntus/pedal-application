package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.request.AddBoardRequestDto;
import entus.resourceServer.domain.dto.request.DeletePedalRequestDto;
import entus.resourceServer.domain.dto.request.MovePedalRequestDto;
import entus.resourceServer.domain.dto.request.SwapPedalRequestDto;
import entus.resourceServer.domain.dto.response.BoardDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addBoard(Authentication authentication, @RequestBody AddBoardRequestDto dto){
        User user = userService.get(Long.parseLong((String) authentication.getPrincipal()));

        Board board = Board.create(user);
        board.changeName(dto.getName());
        board.changeDescription(dto.getDescription());
        board.changeImageUrl(dto.getImageUrl());

        Long boardId = boardService.add(board);

        return ResponseEntity.ok().body(boardId);
    }

    @PostMapping("/{boardId}/add/{pedalId}")
    public BoardDetailDto addPedal(@PathVariable Long boardId, @PathVariable Long pedalId){
        Board board = boardService.addPedal(boardId, pedalId);
        return new BoardDetailDto(board);
    }

    @PostMapping("/{boardId}/delete")
    public BoardDetailDto deletePedal(@PathVariable Long boardId, @RequestBody DeletePedalRequestDto dto) {
        Board board = boardService.removePedal(boardId, dto.getPedalId());
        return new BoardDetailDto(board);
    }

    @PostMapping("/{boardId}/move")
    public BoardDetailDto movePedal(@PathVariable Long boardId, @RequestBody MovePedalRequestDto dto) {
        Board board = boardService.movePedal(boardId, dto.getPedalId(), dto.getDestination());
        return new BoardDetailDto(board);
    }

    @PostMapping("/{boardId}/swap")
    public BoardDetailDto swapPedal(@PathVariable Long boardId, @RequestBody SwapPedalRequestDto dto) {
        Board board = boardService.swapPedal(boardId, dto.getPedal1Id(), dto.getPedal2Id());
        return new BoardDetailDto(board);
    }
}
