package entus.resourceServer.Service;

import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board get(Long boardId){
        return boardRepository.findById(boardId).orElse(null);
    }

    public Long add(Board board){
        return boardRepository.save(board).getId();
    }

    public void movePedal(Board board, Pedal pedal, int index){
        board.movePedal(pedal, index);
    }

    public void swapPedal(Board board, Pedal pedal1, Pedal pedal2){
        board.swapPedal(pedal1, pedal2);
    }

    public void removePedal(Board board, Pedal pedal){
        board.removePedal(pedal);
    }
}
