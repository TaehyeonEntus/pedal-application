package entus.resourceServer.repository;

import entus.resourceServer.domain.FavoriteBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoard, Long> {
}
