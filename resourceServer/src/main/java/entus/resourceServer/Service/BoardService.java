package entus.resourceServer.Service;

import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PedalService pedalService;

    @Transactional
    public Board update(Long boardId, String name, String description) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.changeName(name);
        board.changeDescription(description);
        return board;
    }

    public Board get(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public Page<Board> getAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Long add(Board board) {
        return boardRepository.save(board).getId();
    }

    @Transactional
    public Board insertPedal(long boardId, long pedalId, int index) {
        Board board = this.get(boardId);
        Pedal pedal = pedalService.get(pedalId);
        board.insertPedal(pedal, index);
        return board;
    }

    @Transactional
    public Board movePedal(long boardId, long pedalId, int index) {
        Board board = this.get(boardId);
        Pedal pedal = pedalService.get(pedalId);
        board.movePedal(pedal, index);
        return board;
    }

    @Transactional
    public Board swapPedal(long boardId, long pedal1Id, long pedal2Id) {
        Board board = this.get(boardId);
        Pedal pedal1 = pedalService.get(pedal1Id);
        Pedal pedal2 = pedalService.get(pedal2Id);
        board.swapPedal(pedal1, pedal2);
        return board;
    }

    @Transactional
    public Board removePedal(long boardId, long pedalId) {
        Board board = this.get(boardId);
        Pedal pedal = pedalService.get(pedalId);
        board.removePedal(pedal);
        return board;
    }

    public List<Board> get20() {
        return boardRepository.findTop20ByOrderByIdDesc();
    }
}
