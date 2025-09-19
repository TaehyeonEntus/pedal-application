package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id")
    private Long id;

    private String name;

    private String homepage;

    protected Brand(String name, String homepage) {
        this.name = name;
        this.homepage = homepage;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static Brand create(String name, String homepage) {
        return new Brand(name, homepage);
    }
}
