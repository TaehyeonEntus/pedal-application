package entus.resourceServer.Service;

import entus.resourceServer.domain.Category;
import entus.resourceServer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category get(Long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Long add(Category category){
        return categoryRepository.save(category).getId();
    }
}