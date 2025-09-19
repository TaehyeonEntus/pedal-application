package entus.resourceServer.controller;

import entus.resourceServer.Service.*;
import entus.resourceServer.domain.*;
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
    private final BrandService brandService;
    private final CategoryService categoryService;

    @GetMapping("/addTestData")
    @Transactional
    public String addTestData(Authentication authentication) {
        User user = userService.get(Long.parseLong((String) authentication.getPrincipal()));
        Long categoryId = categoryService.add(Category.create("카테고리1", null));
        Long brandId = brandService.add(Brand.create("브랜드1", "www.naver.com"));
        Long p1 = pedalService.add(Pedal.create("pedal1", "설명1", brandService.get(brandId), categoryService.get(categoryId)));
        Long p2 = pedalService.add(Pedal.create("pedal2", "설명2", brandService.get(brandId), categoryService.get(categoryId)));
        Long p3 = pedalService.add(Pedal.create("pedal3", "설명3", brandService.get(brandId), categoryService.get(categoryId)));
        Long p4 = pedalService.add(Pedal.create("pedal4", "설명4", brandService.get(brandId), categoryService.get(categoryId)));
        Long p5 = pedalService.add(Pedal.create("pedal5", "설명5", brandService.get(brandId), categoryService.get(categoryId)));
        Long boardId = boardService.add(Board.create(user, "board1", "설명1"));
        boardService.get(boardId).addPedal(pedalService.get(p1));
        boardService.get(boardId).addPedal(pedalService.get(p2));
        boardService.get(boardId).addPedal(pedalService.get(p3));
        boardService.get(boardId).addPedal(pedalService.get(p4));
        boardService.get(boardId).addPedal(pedalService.get(p5));
        return "redirect:/home";
    }
}
