package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 중간테이블 Board - Pedal
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPedal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedal_id")
    private Pedal pedal;

    private int orderIndex; // 보드 내 페달 순서

    protected BoardPedal(Board board, Pedal pedal, int orderIndex) {
        this.board = board;
        this.pedal = pedal;
        this.orderIndex = orderIndex;
    }

    //<-- 객체 생성 정적 메서드 -->
    public static BoardPedal create(Board board, Pedal pedal, int orderIndex) {
        return new BoardPedal(board, pedal, orderIndex);
    }

    //<-- 수정 메서드 -->
    public void changeOrderIndex(int index) {
        this.orderIndex = index;
    }
}
