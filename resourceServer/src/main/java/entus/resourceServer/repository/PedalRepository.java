package entus.resourceServer.repository;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedalRepository extends JpaRepository<Pedal, Long> {
    List<Pedal> findByCategory(Category category);

    List<Pedal> findTop20ByOrderByIdDesc();
}
