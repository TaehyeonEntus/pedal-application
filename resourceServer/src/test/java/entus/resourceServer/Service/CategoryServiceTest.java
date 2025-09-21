package entus.resourceServer.Service;

import entus.resourceServer.config.R2Config;
import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.dto.response.CategoryDto;
import entus.resourceServer.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @MockitoBean
    R2Config r2Config;
    @MockitoBean
    R2Service r2Service;

    @BeforeEach
    void setUp() {
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

        Long category3Id = categoryService.add(Category.create("카테고리3", null));
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
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("GetRootCategories")
    @Transactional
    void GetRootCategories() throws Exception {
        //given
        Set<CategoryDto> categoryTree = categoryService.getCategoryTree();

        //then
        assertEquals(categoryTree.size(), 4);
    }

    @Test
    @DisplayName("GetCategoryTree")
    @Transactional
    void GetCategoryTree() throws Exception {
        //given
        Set<CategoryDto> c_d0 = categoryService.getCategoryTree();
        Set<CategoryDto> c_d1 = c_d0.stream().flatMap(c -> c.getChildren().stream()).collect(Collectors.toSet());
        Set<CategoryDto> c_d2 = c_d1.stream().flatMap(c -> c.getChildren().stream()).collect(Collectors.toSet());

        //then
        assertEquals(c_d0.size(), 4);
        assertEquals(c_d1.size(), 20);
        assertEquals(c_d2.size(), 0);
    }
}