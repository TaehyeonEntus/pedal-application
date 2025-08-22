package entus.resourceServer.domain;

import entus.resourceServer.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardPedalTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardPedalRepository boardPedalRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PedalRepository pedalRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("GetId")
    @Transactional
    void GetId() throws Exception {
        //given
        User user = User.create(1L);
        userRepository.save(user);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Pedal pedal1 = Pedal.create("페달1", "설명1", category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", category);
        pedalRepository.save(pedal2);

        Board board = Board.create(user);
        boardRepository.save(board);

        BoardPedal boardPedal1 = BoardPedal.create(board, pedal1, 1);
        BoardPedal boardPedal2 = BoardPedal.create(board, pedal2, 2);

        BoardPedal saved1 = boardPedalRepository.save(boardPedal1);
        BoardPedal saved2 = boardPedalRepository.save(boardPedal2);

        //when
        Long savedId1 = saved1.getId();
        Long savedId2 = saved2.getId();

        //then
        assertNotNull(savedId1);
        assertNotNull(savedId2);
    }

    @Test
    @DisplayName("GetBoard")
    @Transactional
    void GetBoard() throws Exception {
        //given
        User user = User.create(1L);
        userRepository.save(user);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Pedal pedal1 = Pedal.create("페달1", "설명1", category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", category);
        pedalRepository.save(pedal2);

        Board board = Board.create(user);
        boardRepository.save(board);

        BoardPedal boardPedal1 = BoardPedal.create(board, pedal1, 1);
        BoardPedal boardPedal2 = BoardPedal.create(board, pedal2, 2);

        BoardPedal saved1 = boardPedalRepository.save(boardPedal1);
        BoardPedal saved2 = boardPedalRepository.save(boardPedal2);

        //when
        Board getBoard1 = saved1.getBoard();
        Board getBoard2 = saved2.getBoard();

        //then
        assertEquals(board, getBoard1);
        assertEquals(board, getBoard2);
    }

    @Test
    @DisplayName("GetPedal")
    @Transactional
    void GetPedal() throws Exception {
        //given
        User user = User.create(1L);
        userRepository.save(user);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Pedal pedal1 = Pedal.create("페달1", "설명1", category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", category);
        pedalRepository.save(pedal2);

        Board board = Board.create(user);
        boardRepository.save(board);

        BoardPedal boardPedal1 = BoardPedal.create(board, pedal1, 1);
        BoardPedal boardPedal2 = BoardPedal.create(board, pedal2, 2);

        BoardPedal saved1 = boardPedalRepository.save(boardPedal1);
        BoardPedal saved2 = boardPedalRepository.save(boardPedal2);

        //when
        Pedal getPedal1 = saved1.getPedal();
        Pedal getPedal2 = saved2.getPedal();

        //then
        assertEquals(pedal1, getPedal1);
        assertEquals(pedal2, getPedal2);
    }

    @Test
    @DisplayName("GetOrderIndex")
    @Transactional
    void GetOrderIndex() throws Exception {
        //given
        User user = User.create(1L);
        userRepository.save(user);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Pedal pedal1 = Pedal.create("페달1", "설명1", category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", category);
        pedalRepository.save(pedal2);

        Board board = Board.create(user);
        boardRepository.save(board);

        BoardPedal boardPedal1 = BoardPedal.create(board, pedal1, 1);
        BoardPedal boardPedal2 = BoardPedal.create(board, pedal2, 2);

        BoardPedal saved1 = boardPedalRepository.save(boardPedal1);
        BoardPedal saved2 = boardPedalRepository.save(boardPedal2);

        //when
        int getOrderIndex1 = saved1.getOrderIndex();
        int getOrderIndex2 = saved2.getOrderIndex();

        //then
        assertEquals(1, getOrderIndex1);
        assertEquals(2, getOrderIndex2);
    }
}