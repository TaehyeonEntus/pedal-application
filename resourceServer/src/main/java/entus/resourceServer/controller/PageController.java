package entus.resourceServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/board/{boardId}")
    public String boardDetail(@PathVariable String boardId) {
        return "viewBoard";
    }

    @GetMapping("/board")
    public String board() {
        return "viewBoards";
    }

    @GetMapping("/pedal/{pedalId}")
    public String pedalDetail(@PathVariable String pedalId) {
        return "viewPedal";
    }

    @GetMapping("/pedal")
    public String pedal() {
        return "viewPedals";
    }
}
