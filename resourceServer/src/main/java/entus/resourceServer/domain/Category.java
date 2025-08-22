package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private List<Category> children = new ArrayList<>();


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
