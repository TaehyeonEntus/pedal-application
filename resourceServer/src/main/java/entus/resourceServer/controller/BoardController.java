package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.R2Service;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.request.*;
import entus.resourceServer.domain.dto.response.*;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/{boardId}/update")
    public BoardInfoDto updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardInfoRequestDto dto) {
        return new BoardInfoDto(boardService.update(boardId, dto.getName(), dto.getDescription()));
    }

    @PostMapping("/{boardId}/upload")
    public PresignedUrlResponseDto uploadBoardImage(@PathVariable Long boardId) {
        String objectKey = "board_" + boardId + ".jpg";
        return new PresignedUrlResponseDto(r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5)), objectKey);
    }

    @PostMapping("/{boardId}/upload/success")
    public ApiResponse uploadImageSuccess(@PathVariable Long boardId, @RequestBody UploadImageSuccessRequestDto dto) {
        r2Service.setBoardImageUrl(boardId, dto.getObjectKey());
        return new ApiResponse("upload success");
    }

    @PostMapping("/{boardId}/insert")
    public BoardChainDto addPedal(@PathVariable Long boardId, @RequestBody InsertPedalRequestDto dto) {
        return new BoardChainDto(boardService.insertPedal(boardId, dto.getPedalId(),dto.getIndex()));
    }

    @PostMapping("/{boardId}/delete")
    public BoardChainDto deletePedal(@PathVariable Long boardId, @RequestBody DeletePedalRequestDto dto) {
        return new BoardChainDto(boardService.removePedal(boardId, dto.getPedalId()));
    }

    @PostMapping("/{boardId}/move")
    public BoardChainDto movePedal(@PathVariable Long boardId, @RequestBody MovePedalRequestDto dto) {
        return new BoardChainDto(boardService.movePedal(boardId, dto.getPedalId(), dto.getDestination()));
    }

    @PostMapping("/{boardId}/swap")
    public BoardChainDto swapPedal(@PathVariable Long boardId, @RequestBody SwapPedalRequestDto dto) {
        return new BoardChainDto(boardService.swapPedal(boardId, dto.getPedal1Id(), dto.getPedal2Id()));
    }
}
