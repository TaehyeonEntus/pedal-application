package entus.resourceServer.Service;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.dto.response.CategoryDto;
import entus.resourceServer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category get(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Long add(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Set<CategoryDto> getCategoryTree() {

        return categoryRepository.findCategoryTree().stream().map(CategoryDto::new).collect(Collectors.toSet());
    }
}