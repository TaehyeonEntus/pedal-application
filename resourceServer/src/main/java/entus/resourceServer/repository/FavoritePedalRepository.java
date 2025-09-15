package entus.resourceServer.repository;

import entus.resourceServer.domain.FavoritePedal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePedalRepository extends JpaRepository<FavoritePedal, Long> {
}
