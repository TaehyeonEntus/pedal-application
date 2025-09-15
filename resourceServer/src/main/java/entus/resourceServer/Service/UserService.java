package entus.resourceServer.Service;

import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.User;
import entus.resourceServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final PedalService pedalService;

    public User get(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Long add(User user) {
        return userRepository.save(user).getId();
    }

    public void syncUser(Long userId) {
        userRepository.findById(userId).orElseGet(() -> userRepository.save(User.create(userId)));
    }

    @Transactional
    public User addFavoriteBoard(Long userId, Long boardId) {
        User user = this.get(userId);
        Board board = boardService.get(boardId);
        user.addFavoriteBoard(board);
        return user;
    }

    @Transactional
    public User addFavoritePedal(Long userId, Long pedalId) {
        User user = this.get(userId);
        Pedal pedal = pedalService.get(pedalId);
        user.addFavoritePedal(pedal);
        return user;
    }
}
