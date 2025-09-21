package entus.resourceServer.repository;

import entus.resourceServer.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select distinct c from Category c " +   //d0
            "left join fetch c.children c1 " +      //d1
            "left join fetch c1.children c2 " +     //d2 (말단의 children, isEmpty로 leaf인지 확인함)
            "where c.parent is null")
    Set<Category> findCategoryTree();
}
