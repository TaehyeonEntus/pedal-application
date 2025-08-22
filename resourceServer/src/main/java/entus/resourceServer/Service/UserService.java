package entus.resourceServer.Service;

import entus.resourceServer.domain.User;
import entus.resourceServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User get(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Long add(User user) {
        return userRepository.save(user).getId();
    }
}
