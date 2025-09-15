package entus.resourceServer.controller;

import entus.resourceServer.Service.BoardService;
import entus.resourceServer.Service.CategoryService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final PedalService pedalService;
    private final BoardService boardService;
    private final CategoryService categoryService;
    @GetMapping("/")
    public String home(){
        return "redirect:/home";
    }

    @GetMapping("/addTestData")
    @Transactional
    public String addTestData(Authentication authentication){
        User user = userService.get(Long.parseLong((String) authentication.getPrincipal()));
        Long cate = categoryService.add(Category.create("카테고리1", null));
        Long p1 = pedalService.add(Pedal.create("pedal1", "설명1", categoryService.get(cate)));
        Long p2 = pedalService.add(Pedal.create("pedal2", "설명2", categoryService.get(cate)));
        Long p3 = pedalService.add(Pedal.create("pedal3", "설명3", categoryService.get(cate)));
        Long p4 = pedalService.add(Pedal.create("pedal4", "설명4", categoryService.get(cate)));
        Long p5 = pedalService.add(Pedal.create("pedal5", "설명5", categoryService.get(cate)));
        Long boardId = boardService.add(Board.create(user));
        boardService.get(boardId).addPedal(pedalService.get(p1));
        boardService.get(boardId).addPedal(pedalService.get(p2));
        boardService.get(boardId).addPedal(pedalService.get(p3));
        boardService.get(boardId).addPedal(pedalService.get(p4));
        boardService.get(boardId).addPedal(pedalService.get(p5));
        return "redirect:/home";
    }
}
