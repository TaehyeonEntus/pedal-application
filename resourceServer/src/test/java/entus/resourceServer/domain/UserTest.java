package entus.resourceServer.domain;

import entus.resourceServer.repository.BoardRepository;
import entus.resourceServer.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("GetId")
    void GetId() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        User savedUser = userRepository.save(user);

        //when
        Long id = savedUser.getId();

        //then
        assertEquals(userId, id);
    }

    @Test
    @DisplayName("GetName")
    void GetName() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        user.changeName("abc");

        //when
        User savedUser = userRepository.save(user);
        String name = savedUser.getName();

        //then
        assertEquals("abc", name);
    }

    @Test
    @DisplayName("GetBoards")
    void GetBoards() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        User savedUser = userRepository.save(user);

        Board board = Board.create(savedUser);
        Board savedBoard = boardRepository.save(board);

        //when
        Board getBoard = savedUser.getBoards().get(0);

        //then
        assertEquals(savedBoard, getBoard);
    }
}