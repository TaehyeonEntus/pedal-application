package entus.resourceServer.domain.dto.response;

import entus.resourceServer.domain.Category;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Set<CategoryDto> children = new HashSet<>();

    public CategoryDto(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.getName();
        this.children.addAll(category.getChildren().stream().map(CategoryDto::new).collect(Collectors.toSet()));
    }
}
