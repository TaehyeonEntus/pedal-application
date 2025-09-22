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

        Long category1Id = categoryService.add(Category.create("카테고리1", null));
        Category category1 = categoryService.get(category1Id);
        Long category1_1Id = categoryService.add(Category.create("카테고리1_1", category1));
        Long category1_2Id = categoryService.add(Category.create("카테고리1_2", category1));
        Long category1_3Id = categoryService.add(Category.create("카테고리1_3", category1));
        Long category1_4Id = categoryService.add(Category.create("카테고리1_4", category1));
        Long category1_5Id = categoryService.add(Category.create("카테고리1_5", category1));

        Long category2Id = categoryService.add(Category.create("카테고리2", null));
        Category category2 = categoryService.get(category2Id);
        Long category2_1Id = categoryService.add(Category.create("카테고리2_1", category2));
        Long category2_2Id = categoryService.add(Category.create("카테고리2_2", category2));
        Long category2_3Id = categoryService.add(Category.create("카테고리2_3", category2));
        Long category2_4Id = categoryService.add(Category.create("카테고리2_4", category2));
        Long category2_5Id = categoryService.add(Category.create("카테고리2_5", category2));

        Long category3Id = categoryService.add(Category.create("카테고리3",null));
        Category category3 = categoryService.get(category3Id);
        Long category3_1Id = categoryService.add(Category.create("카테고리3_1", category3));
        Long category3_2Id = categoryService.add(Category.create("카테고리3_2", category3));
        Long category3_3Id = categoryService.add(Category.create("카테고리3_3", category3));
        Long category3_4Id = categoryService.add(Category.create("카테고리3_4", category3));
        Long category3_5Id = categoryService.add(Category.create("카테고리3_5", category3));

        Long category4Id = categoryService.add(Category.create("카테고리4", null));
        Category category4 = categoryService.get(category4Id);
        Long category4_1Id = categoryService.add(Category.create("카테고리4_1", category4));
        Long category4_2Id = categoryService.add(Category.create("카테고리4_2", category4));
        Long category4_3Id = categoryService.add(Category.create("카테고리4_3", category4));
        Long category4_4Id = categoryService.add(Category.create("카테고리4_4", category4));
        Long category4_5Id = categoryService.add(Category.create("카테고리4_5", category4));

        Long category5Id = categoryService.add(Category.create("기타", null));

        Long brand1Id = brandService.add(Brand.create("브랜드1", "https://123.com"));
        Long brand2Id = brandService.add(Brand.create("브랜드2", "https://123.com"));
        Long brand3Id = brandService.add(Brand.create("브랜드3", "https://123.com"));
        Long brand4Id = brandService.add(Brand.create("브랜드4", "https://123.com"));

        Long p1 = pedalService.add(Pedal.create("pedal1", "설명1", brandService.get(brand1Id), categoryService.get(category1_1Id)));
        Long p2 = pedalService.add(Pedal.create("pedal2", "설명2", brandService.get(brand2Id), categoryService.get(category2_2Id)));
        Long p3 = pedalService.add(Pedal.create("pedal3", "설명3", brandService.get(brand3Id), categoryService.get(category3_3Id)));
        Long p4 = pedalService.add(Pedal.create("pedal4", "설명4", brandService.get(brand4Id), categoryService.get(category4_4Id)));

        Long boardId = boardService.add(Board.create(user, "board1", "설명1"));

        boardService.get(boardId).insertPedal(pedalService.get(p1),0);
        boardService.get(boardId).insertPedal(pedalService.get(p2),1);
        boardService.get(boardId).insertPedal(pedalService.get(p3),2);
        boardService.get(boardId).insertPedal(pedalService.get(p4),3);

        return "redirect:/home";
    }
}
