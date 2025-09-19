package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.page.BoardDetailPageDto;
import entus.resourceServer.domain.dto.page.HomePageDto;
import entus.resourceServer.domain.dto.page.PedalDetailPageDto;
import entus.resourceServer.domain.dto.response.BoardDetailDto;
import entus.resourceServer.domain.dto.response.BoardListDto;
import entus.resourceServer.domain.dto.response.PedalDetailDto;
import entus.resourceServer.domain.dto.response.PedalListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BoardService boardService;
    private final PedalService pedalService;

    @GetMapping("/home")
    public HomePageDto apiHome() {
        List<Board> boardService20 = boardService.get20();
        List<Pedal> pedalService20 = pedalService.get20();

        return new HomePageDto(
                boardService20
                        .stream()
                        .map(BoardListDto::new)
                        .toList(),
                pedalService20
                        .stream()
                        .map(PedalListDto::new)
                        .toList());
    }

    @GetMapping("/board/{boardId}")
    public BoardDetailPageDto apiBoard(@PathVariable Long boardId) {
        return new BoardDetailPageDto(new BoardDetailDto(boardService.get(boardId)));
    }

    @GetMapping("/pedal/{pedalId}")
    public PedalDetailPageDto apiPedal(@PathVariable Long pedalId) {
        return new PedalDetailPageDto(new PedalDetailDto(pedalService.get(pedalId)));
    }
}
