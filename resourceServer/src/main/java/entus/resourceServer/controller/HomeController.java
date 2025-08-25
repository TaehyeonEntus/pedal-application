package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.BoardListDto;
import entus.resourceServer.domain.dto.HomeDto;
import entus.resourceServer.domain.dto.PedalListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PedalService pedalService;
    private final BoardService boardService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @ResponseBody
    @GetMapping("/api/home")
    public HomeDto apiHome() {
        List<Board> boardService20 = boardService.get20();
        List<Pedal> pedalService20 = pedalService.get20();

        return new HomeDto(
                boardService20
                        .stream()
                        .map(BoardListDto::new)
                        .toList(),
                pedalService20
                        .stream()
                        .map(PedalListDto::new)
                        .toList());
    }
}
