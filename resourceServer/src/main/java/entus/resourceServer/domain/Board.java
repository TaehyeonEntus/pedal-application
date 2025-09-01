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
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardPedal> pedals = new ArrayList<>();

    private String name;

    private String description;

    private String imageUrl;

    protected Board(User user) {
        this.user = user;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static Board create(User user) {
        Board board = new Board(user);
        user.getBoards().add(board);
        board.changeImageUrl("/uploads/boards/board.jpg");
        return board;
    }

    public void addPedal(Pedal pedal) {
        this.pedals.add(BoardPedal.create(this, pedal, getPedals().size()));
    }

    //<-- 비즈니스 메서드 -->
    public void movePedal(Pedal pedal, int newIndex) {
        //인덱스 범위 초과
        if (newIndex >= getPedals().size())
            throw new IndexOutOfBoundsException();

        BoardPedal boardPedal = getPedals().stream()
                .filter(p -> p.getPedal().equals(pedal))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        int oldIndex = boardPedal.getOrderIndex();

        // 왼쪽으로 쉬프트
        if (oldIndex < newIndex) {
            pedals.stream()
                    .filter(bp -> bp.getOrderIndex() > oldIndex && bp.getOrderIndex() <= newIndex)
                    .forEach(bp -> bp.changeOrderIndex(bp.getOrderIndex() - 1));
        }
        // 오른쪽으로 쉬프트
        else if (oldIndex > newIndex) {
            pedals.stream()
                    .filter(bp -> bp.getOrderIndex() >= newIndex && bp.getOrderIndex() < oldIndex)
                    .forEach(bp -> bp.changeOrderIndex(bp.getOrderIndex() + 1));
        }

        boardPedal.changeOrderIndex(newIndex);
    }

    public void swapPedal(Pedal aPedal, Pedal bPedal) {
        BoardPedal a = pedals.stream()
                .filter(bp -> bp.getPedal().equals(aPedal))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        BoardPedal b = pedals.stream()
                .filter(bp -> bp.getPedal().equals(bPedal))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        int aIndex = a.getOrderIndex();
        int bIndex = b.getOrderIndex();

        a.changeOrderIndex(bIndex);
        b.changeOrderIndex(aIndex);
    }

    public void removePedal(Pedal pedal) {
        BoardPedal boardPedal = pedals.stream()
                .filter(bp -> bp.getPedal().equals(pedal))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        int index = boardPedal.getOrderIndex();

        pedals.remove(boardPedal);

        //왼쪽으로 쉬프트
        pedals.stream()
                .filter(bp -> bp.getOrderIndex() > index)
                .forEach(bp -> bp.changeOrderIndex(bp.getOrderIndex() - 1));
    }

    public void changeImageUrl(String newImageUrl){
        this.imageUrl = newImageUrl;
    }
}
