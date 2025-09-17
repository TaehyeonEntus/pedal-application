package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.R2Service;
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

import java.time.Duration;
import java.util.Map;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final R2Service r2Service;

    @PostMapping("/add")
    public ResponseEntity<?> addBoard(Authentication authentication, @RequestBody AddBoardRequestDto dto) {
        User user = userService.get(Long.parseLong((String) authentication.getPrincipal()));
        Long boardId = boardService.add(Board.create(user, dto.getName(), dto.getDescription()));

        return ResponseEntity.ok().body(boardId);
    }

    @PostMapping("/{boardId}/upload")
    public ResponseEntity<?> uploadBoard(@PathVariable Long boardId) {
        String objectKey = "board_" + boardId;
        r2Service.setBoardImageUrl(boardId, objectKey);
        return ResponseEntity.ok(Map.of("uploadUrl", r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5))));
    }

    @PostMapping("/{boardId}/add/{pedalId}")
    public BoardDetailDto addPedal(@PathVariable Long boardId, @PathVariable Long pedalId) {
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
