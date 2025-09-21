package entus.resourceServer.domain;

import entus.resourceServer.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PedalRepository pedalRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        Board savedBoard = boardRepository.save(board);

        //when
        Long boardId = savedBoard.getId();

        //then
        assertNotNull(boardId);
    }

    @Test
    @DisplayName("GetUser")
    void GetUser() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        Board savedBoard = boardRepository.save(board);

        //when
        User savedUser = savedBoard.getUser();

        //then
        assertEquals(user, savedUser);
    }

    @Test
    @DisplayName("GetPedals")
    void GetPedals() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        User savedUser = userRepository.save(user);

        Board board = Board.create(savedUser, "board1", "설명1");
        Board savedBoard = boardRepository.save(board);

        Category category = Category.create("drive", null);
        Brand brand = Brand.create("브랜드1", "www.naver.com");
        Pedal pedal1 = Pedal.create("fuzz", "찌지직", brand, category);
        Pedal pedal2 = Pedal.create("tube screamer", "우에엥", brand, category);

        Pedal savedPedal1 = pedalRepository.save(pedal1);
        Pedal savedPedal2 = pedalRepository.save(pedal2);

        savedBoard.getPedals().add(BoardPedal.create(savedBoard, savedPedal1, 1));
        savedBoard.getPedals().add(BoardPedal.create(savedBoard, savedPedal2, 2));

        //when
        List<BoardPedal> pedals = savedBoard.getPedals();

        Pedal getPedal1 = pedals.get(0).getPedal();
        Pedal getPedal2 = pedals.get(1).getPedal();

        //then
        assertEquals(savedPedal1, getPedal1);
        assertEquals(savedPedal2, getPedal2);

        assertEquals(pedals.size(), 2);
    }

    @Test
    @DisplayName("AddPedal")
    @Transactional
    void AddPedal() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        boardRepository.save(board);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Brand brand = Brand.create("브랜드1","www.naver.com");
        brandRepository.save(brand);

        Pedal pedal = Pedal.create("페달1", "설명1", brand, category);
        pedalRepository.save(pedal);

        //when
        board.insertPedal(pedal,0);
        BoardPedal boardPedal = board.getPedals().get(0);

        //then
        assertEquals(boardPedal.getPedal(), pedal);
    }

    @Test
    @DisplayName("MovePedal")
    @Transactional
    void MovePedal() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        boardRepository.save(board);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Brand brand = Brand.create("브랜드1","www.naver.com");
        brandRepository.save(brand);

        Pedal pedal1 = Pedal.create("페달1", "설명1", brand, category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", brand, category);
        pedalRepository.save(pedal2);

        Pedal pedal3 = Pedal.create("페달3", "설명3", brand, category);
        pedalRepository.save(pedal3);

        Pedal pedal4 = Pedal.create("페달4", "설명4", brand, category);
        pedalRepository.save(pedal4);

        Pedal pedal5 = Pedal.create("페달5", "설명5", brand, category);
        pedalRepository.save(pedal5);

        //when
        board.insertPedal(pedal1,0);
        board.insertPedal(pedal2,1);
        board.insertPedal(pedal3,2);
        board.insertPedal(pedal4,3);
        board.insertPedal(pedal5,4);

        board.movePedal(pedal5, 0);

        BoardPedal boardPedal1 = board.getPedals().get(0);  //1
        BoardPedal boardPedal2 = board.getPedals().get(1);  //2
        BoardPedal boardPedal3 = board.getPedals().get(2);  //3
        BoardPedal boardPedal4 = board.getPedals().get(3);  //4
        BoardPedal boardPedal5 = board.getPedals().get(4);  //0

        //then
        assertEquals(boardPedal1.getOrderIndex(), 1);
        assertEquals(boardPedal2.getOrderIndex(), 2);
        assertEquals(boardPedal3.getOrderIndex(), 3);
        assertEquals(boardPedal4.getOrderIndex(), 4);
        assertEquals(boardPedal5.getOrderIndex(), 0);
    }

    @Test
    @DisplayName("SwapPedal")
    @Transactional
    void SwapPedal() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        boardRepository.save(board);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Brand brand = Brand.create("브랜드1","www.naver.com");
        brandRepository.save(brand);

        Pedal pedal1 = Pedal.create("페달1", "설명1", brand, category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", brand, category);
        pedalRepository.save(pedal2);

        Pedal pedal3 = Pedal.create("페달3", "설명3", brand, category);
        pedalRepository.save(pedal3);

        Pedal pedal4 = Pedal.create("페달4", "설명4", brand, category);
        pedalRepository.save(pedal4);

        Pedal pedal5 = Pedal.create("페달5", "설명5", brand, category);
        pedalRepository.save(pedal5);
        //when
        board.insertPedal(pedal1,0);
        board.insertPedal(pedal2,1);
        board.insertPedal(pedal3,2);
        board.insertPedal(pedal4,3);
        board.insertPedal(pedal5,4);

        board.swapPedal(pedal2, pedal4);

        BoardPedal boardPedal1 = board.getPedals().get(0);  //0
        BoardPedal boardPedal2 = board.getPedals().get(1);  //3
        BoardPedal boardPedal3 = board.getPedals().get(2);  //2
        BoardPedal boardPedal4 = board.getPedals().get(3);  //1
        BoardPedal boardPedal5 = board.getPedals().get(4);  //4

        //then
        assertEquals(boardPedal1.getOrderIndex(), 0);
        assertEquals(boardPedal2.getOrderIndex(), 3);
        assertEquals(boardPedal3.getOrderIndex(), 2);
        assertEquals(boardPedal4.getOrderIndex(), 1);
        assertEquals(boardPedal5.getOrderIndex(), 4);
    }

    @Test
    @DisplayName("RemovePedal")
    void RemovePedal() throws Exception {
        //given
        Long userId = 1L;
        User user = User.create(userId);
        userRepository.save(user);

        Board board = Board.create(user, "board1", "설명1");
        boardRepository.save(board);

        Category category = Category.create("카테고리1", null);
        categoryRepository.save(category);

        Brand brand = Brand.create("브랜드1","www.naver.com");
        brandRepository.save(brand);

        Pedal pedal1 = Pedal.create("페달1", "설명1", brand, category);
        pedalRepository.save(pedal1);

        Pedal pedal2 = Pedal.create("페달2", "설명2", brand, category);
        pedalRepository.save(pedal2);

        Pedal pedal3 = Pedal.create("페달3", "설명3", brand, category);
        pedalRepository.save(pedal3);

        Pedal pedal4 = Pedal.create("페달4", "설명4", brand, category);
        pedalRepository.save(pedal4);

        Pedal pedal5 = Pedal.create("페달5", "설명5", brand, category);
        pedalRepository.save(pedal5);

        //when
        board.insertPedal(pedal1,0);
        board.insertPedal(pedal2,1);
        board.insertPedal(pedal3,2);
        board.insertPedal(pedal4,3);
        board.insertPedal(pedal5,4);

        board.removePedal(pedal3);

        //then
        assertEquals(board.getPedals().size(), 4);
    }
}