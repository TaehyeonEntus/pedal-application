package entus.resourceServer.domain;

import entus.resourceServer.repository.BrandRepository;
import entus.resourceServer.repository.CategoryRepository;
import entus.resourceServer.repository.PedalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PedalTest {
    @Autowired
    PedalRepository pedalRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrandRepository brandRepository;

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
        Category category = Category.create("카테고리1", null);
        Brand brand = Brand.create("브랜드1", null);
        Pedal pedal = Pedal.create("페달1", "설명1", brand, category);

        //when
        categoryRepository.save(category);
        brandRepository.save(brand);
        Long pedalId = pedalRepository.save(pedal).getId();

        //then
        assertNotNull(pedalId);
    }

    @Test
    @DisplayName("GetName")
    void GetName() throws Exception {
        //given
        Category category = Category.create("카테고리1", null);
        Brand brand = Brand.create("브랜드1", null);
        Pedal pedal = Pedal.create("페달1", "설명1", brand, category);

        //when
        categoryRepository.save(category);
        brandRepository.save(brand);
        String pedalName = pedalRepository.save(pedal).getName();

        //then
        assertNotNull(pedalName, pedal.getName());
    }

    @Test
    @DisplayName("GetDescription")
    void GetDescription() throws Exception {
        //given
        Category category = Category.create("카테고리1", null);
        Brand brand = Brand.create("브랜드1", null);
        Pedal pedal = Pedal.create("페달1", "설명1", brand, category);

        //when
        categoryRepository.save(category);
        brandRepository.save(brand);
        String pedalDescription = pedalRepository.save(pedal).getDescription();

        //then
        assertNotNull(pedalDescription, pedal.getDescription());
    }

    @Test
    @DisplayName("GetCategory")
    void GetCategory() throws Exception {
        Category category = Category.create("카테고리1", null);
        Brand brand = Brand.create("브랜드1", null);
        Pedal pedal = Pedal.create("페달1", "설명1", brand, category);

        //when
        brandRepository.save(brand);
        Category savedCategory = categoryRepository.save(category);
        Pedal savedPedal = pedalRepository.save(pedal);

        //then
        assertEquals(savedPedal.getCategory(), savedCategory);
    }
}