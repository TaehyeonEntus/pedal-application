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

    @Id
    //cookie에서 userId 가져와서 꼽기
    @Column(name = "user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    protected User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //<-- 객체 생성 정적 메서드 -->
    public static User create(Long id) {
        return new User(id, "default");
    }

    //<-- 수정 메서드 -->
    public void changeName(String name) {
        this.name = name;
    }
}
