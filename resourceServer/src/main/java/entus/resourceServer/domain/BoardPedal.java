package entus.resourceServer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPedal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "pedal_id")
    private Pedal pedal;

    private int orderIndex; // 보드 내 페달 순서

    public static BoardPedal create(Board board, Pedal pedal, int orderIndex) {
        return new BoardPedal(board, pedal, orderIndex);
    }

    protected BoardPedal(Board board, Pedal pedal, int orderIndex) {
        this.board = board;
        this.pedal = pedal;
        this.orderIndex = orderIndex;
    }
}
