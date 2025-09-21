package entus.resourceServer.repository;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedalRepository extends JpaRepository<Pedal, Long> {
    Page<Pedal> findAllByCategory(Category category, Pageable pageable);
    List<Pedal> findTop20ByOrderByIdDesc();
}
