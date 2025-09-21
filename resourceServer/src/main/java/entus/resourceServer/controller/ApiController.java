package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.CategoryService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.User;
import entus.resourceServer.domain.dto.page.*;
import entus.resourceServer.domain.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BoardService boardService;
    private final PedalService pedalService;
    private final CategoryService categoryService;

    @GetMapping("/check")
    public ApiResponse check() {
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

    @GetMapping("/pedals/category/{categoryId}")
    public PedalListPageDto apiPedalCategory(@PathVariable long categoryId, @RequestParam(defaultValue = "0") int page) {
        Page<Pedal> pedals = pedalService.getAllByCategory(categoryService.get(categoryId), PageRequest.of(page, 20, Sort.by("id").ascending()));
        return new PedalListPageDto(
                pedals.getTotalPages(),
                pedals.getNumber(),
                pedals
                        .stream()
                        .map(PedalListDto::new)
                        .toList());
    }

    @GetMapping("/categories")
    public Set<CategoryDto> apiCategories() {
        return categoryService.getCategoryTree();
    }

    @GetMapping("/board/{boardId}")
    public BoardDetailPageDto apiBoardDetail(Authentication authentication, @PathVariable Long boardId) {
        Board board = boardService.get(boardId);
        boolean owner = board.getUser().getId().equals(Long.parseLong((String) authentication.getPrincipal()));
        return new BoardDetailPageDto(new BoardDetailDto(boardService.get(boardId)), owner);
    }

    @GetMapping("/board/{boardId}/info")
    public BoardInfoDto apiBoardInfo(@PathVariable Long boardId) {
        return new BoardInfoDto(boardService.get(boardId));
    }

    @GetMapping("/board/{boardId}/chain")
    public BoardChainDto apiBoardChain(@PathVariable Long boardId) {
        return new BoardChainDto(boardService.get(boardId));
    }

    @GetMapping("/pedal/{pedalId}")
    public PedalDetailPageDto apiPedalDetail(@PathVariable Long pedalId) {
        return new PedalDetailPageDto(new PedalDetailDto(pedalService.get(pedalId)));
    }
}
