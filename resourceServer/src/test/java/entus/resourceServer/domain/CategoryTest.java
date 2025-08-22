package entus.resourceServer.domain;

import entus.resourceServer.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("GetId")
    void GetId() throws Exception {
        //given
        Category category = Category.create("drive",null);

        Category savedParent = categoryRepository.save(category);

        //when
        Long id = savedParent.getId();

        Optional<Category> oCategory = categoryRepository.findById(id);
        //then
        assertNotNull(oCategory.get().getId());
    }

    @Test
    @DisplayName("GetName")
    void GetName() throws Exception {
        //given
        Category category = Category.create("drive",null);

        Category savedParent = categoryRepository.save(category);

        //when
        Long id = savedParent.getId();

        Optional<Category> oCategory = categoryRepository.findById(id);

        //then
        assertEquals(category.getName(), oCategory.get().getName());
    }

    @Test
    @DisplayName("GetParent")
    @Transactional
    void GetParent() throws Exception {
        //given
        Category pCategory = Category.create("drive",null);
        Category cCategory = Category.create("overdrive",pCategory);

        Category savedParent = categoryRepository.save(pCategory);
        Category savedChild = categoryRepository.save(cCategory);
        //when
        Long parentId = savedParent.getId();
        Long childId = savedChild.getId();

        Optional<Category> oParent = categoryRepository.findById(parentId);
        Optional<Category> oChild = categoryRepository.findById(childId);

        //then
        assertEquals(oChild.get().getParent(),oParent.get());

    }

    @Test
    @DisplayName("GetChildren")
    @Transactional
    void GetChildren() throws Exception {
        //given
        Category pCategory = Category.create("drive",null);
        Category cCategory = Category.create("overdrive",pCategory);

        Category savedParent = categoryRepository.save(pCategory);
        Category savedChild = categoryRepository.save(cCategory);

        //when
        Long parentId = savedParent.getId();
        Long childId = savedChild.getId();

        Optional<Category> oParent = categoryRepository.findById(parentId);
        Optional<Category> oChild = categoryRepository.findById(childId);

        //then
        assertTrue(oParent.get().getChildren().contains(oChild.get()));
    }
}