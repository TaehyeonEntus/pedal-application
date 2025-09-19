package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Pedal은 Board에 속할 수도, 속하지 않을 수 있음
 * Board에서는 그냥 외래키만 가져다 쓰는 방식
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String imageUrl;

    protected Pedal(String name, String description, Brand brand, Category category) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
    }

    //<-- 객체 생성 정적 메서드 -->
    public static Pedal create(String name, String description, Brand brand, Category category) {
        Pedal pedal = new Pedal(name, description, brand, category);
        pedal.changeImageUrl("pedal_default");
        return pedal;
    }

    //<-- 수정 메서드 -->
    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changeBrand(Brand newBrand) {
        this.brand = newBrand;
    }

    public void changeCategory(Category newCategory) {
        this.category = newCategory;
    }

    public void changeImageUrl(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }
}
