package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.page.*;
import entus.resourceServer.domain.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BoardService boardService;
    private final PedalService pedalService;
    @GetMapping("/check")
    public ApiResponse check(){
        return new ApiResponse("success token check");
    }

    @GetMapping("/home")
    public HomePageDto apiHome() {
        List<Board> hotBoards = boardService.get20();
        List<Pedal> hotPedals = pedalService.get20();

        return new HomePageDto(
                hotBoards
                        .stream()
                        .map(BoardListDto::new)
                        .toList(),
                hotPedals
                        .stream()
                        .map(PedalListDto::new)
                        .toList());
    }

    @GetMapping("/boards")
    public BoardListPageDto apiBoardList(@RequestParam(defaultValue = "0") int page) {
        Page<Board> boards = boardService.getAll(PageRequest.of(page, 20, Sort.by("id").ascending()));
        return new BoardListPageDto(
                boards.getTotalPages(),
                boards.getNumber(),
                boards
                        .stream()
                        .map(BoardListDto::new)
                        .toList());
    }

    @GetMapping("/pedals")
    public PedalListPageDto apiPedalList(@RequestParam(defaultValue = "0") int page) {
        Page<Pedal> pedals = pedalService.getAll(PageRequest.of(page, 20, Sort.by("id").ascending()));
        return new PedalListPageDto(
                pedals.getTotalPages(),
                pedals.getNumber(),
                pedals
                        .stream()
                        .map(PedalListDto::new)
                        .toList());
    }

    @GetMapping("/board/{boardId}")
    public BoardDetailPageDto apiBoardDetail(@PathVariable Long boardId) {
        return new BoardDetailPageDto(new BoardDetailDto(boardService.get(boardId)));
    }

    @GetMapping("/pedal/{pedalId}")
    public PedalDetailPageDto apiPedalDetail(@PathVariable Long pedalId) {
        return new PedalDetailPageDto(new PedalDetailDto(pedalService.get(pedalId)));
    }
}
