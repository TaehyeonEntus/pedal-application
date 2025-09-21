package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 카테고리의 계층은 layer 하나로 구현하되 부모, 자식의 여부로 알 수 있음
 * depth는 직접적으로 판단할 수 없음, 그저 뒤를 돌아볼 뿐...
 * enum으로 구현하는 방식, depth로 판단하는 방식도 있었는데
 * 각 계층별로 기타(etc) 카테고리를 만들기 위해서 이 방식을 사용함 (말단 카테고리 말고 중간 카테고리도 사용할 수 있게)
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();


    protected Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static Category create(String name, Category parent) {
        Category category = new Category(name, parent);
        if (parent != null)
            parent.getChildren().add(category);
        return category;
    }
}
