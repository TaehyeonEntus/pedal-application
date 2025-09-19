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
        return "boardDetail";
    }

    @GetMapping("/board")
    public String board() {
        return "boardList";
    }

    @GetMapping("/pedal")
    public String pedal() {
        return "pedalList";
    }

    @GetMapping("/pedal/{pedalId}")
    public String pedalDetail(@PathVariable String pedalId) {
        return "pedalDetail";
    }
}
