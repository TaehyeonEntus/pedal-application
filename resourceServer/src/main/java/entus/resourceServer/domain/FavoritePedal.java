package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoritePedal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedal_id")
    private Pedal pedal;

    protected FavoritePedal(User user, Pedal pedal) {
        this.user = user;
        this.pedal = pedal;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static FavoritePedal create(User user, Pedal pedal) {
        return new FavoritePedal(user,pedal);
    }
}
