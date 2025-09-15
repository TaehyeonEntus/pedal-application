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
@Table(name = "users")
public class User extends BaseEntity{
    //cookie에서 userId 가져와서 꼽기
    @Id
    @Column(name = "user_id")
    private Long id;

    private String name;

    //소유한 boards
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Board> boards = new ArrayList<>();

    //즐겨찾기 board
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FavoriteBoard> favoriteBoards = new ArrayList<>();

    //즐겨찾기 pedal
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FavoritePedal> favoritePedals = new ArrayList<>();


    protected User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //<-- 객체 생성 정적 메서드 + 편의 메서드 -->
    public static User create(Long id) {
        return new User(id, "default");
    }

    public void addFavoriteBoard(Board board) {
        this.favoriteBoards.add(FavoriteBoard.create(this, board));
    }

    public void addFavoritePedal(Pedal pedal) {
        this.favoritePedals.add(FavoritePedal.create(this, pedal));
    }

    //<-- 수정 메서드 -->
    public void changeName(String name) {
        this.name = name;
    }
}
