package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.R2Service;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.request.*;
import entus.resourceServer.domain.dto.response.AddBoardResponseDto;
import entus.resourceServer.domain.dto.response.ApiResponse;
import entus.resourceServer.domain.dto.response.BoardDetailDto;
import entus.resourceServer.domain.dto.response.PresignedUrlResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    private final R2Service r2Service;

    @PostMapping("/add")
    public AddBoardResponseDto addBoard(Authentication authentication, @RequestBody AddBoardRequestDto dto) {
        User user = userService.get(Long.parseLong((String) authentication.getPrincipal()));
        Long boardId = boardService.add(Board.create(user, dto.getName(), dto.getDescription()));
        return new AddBoardResponseDto(boardId);
    }

    @PostMapping("/{boardId}/upload")
    public PresignedUrlResponseDto uploadBoardImage(@PathVariable Long boardId) {
        String objectKey = "board_" + boardId;
        return new PresignedUrlResponseDto(r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5)));
    }

    @PostMapping("/{boardId}/upload/success")
    public ApiResponse uploadImageSuccess(@PathVariable Long boardId, @RequestBody UploadImageSuccessRequestDto dto) {
        r2Service.setBoardImageUrl(boardId, dto.getObjectKey());
        return new ApiResponse("upload success");
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
