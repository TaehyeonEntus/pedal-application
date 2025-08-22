package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedal extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Pedal(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    //<-- 객체 생성 정적 메서드 -->
    public static Pedal create(String name, String description, Category category) {
        return new Pedal(name,description,category);
    }

    //<-- 수정 메서드 -->
    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changeCategory(Category newCategory) {
        this.category = newCategory;
    }
}
