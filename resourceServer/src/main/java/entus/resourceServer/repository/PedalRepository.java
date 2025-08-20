package entus.resourceServer.repository;

import entus.resourceServer.domain.Pedal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedalRepository extends JpaRepository<Pedal, Long> {
}
