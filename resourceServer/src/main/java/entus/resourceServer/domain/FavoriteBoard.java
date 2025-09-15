package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    protected FavoriteBoard(User user, Board board) {
        this.user = user;
        this.board = board;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static FavoriteBoard create(User user, Board board) {
        return new FavoriteBoard(user,board);
    }
}
