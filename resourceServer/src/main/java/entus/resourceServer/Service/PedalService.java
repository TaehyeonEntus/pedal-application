package entus.resourceServer.Service;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.repository.PedalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedalService {
    private final PedalRepository pedalRepository;

    public Pedal get(Long pedalId) {
        return pedalRepository.findById(pedalId).orElse(null);
    }

    public Long add(Pedal pedal) {
        return pedalRepository.save(pedal).getId();
    }

    public Page<Pedal> getAll(Pageable pageable) {
        return pedalRepository.findAll(pageable);
    }

    public Page<Pedal> getAllByCategory(Category category, Pageable pageable) {
        return pedalRepository.findAllByCategory(category, pageable);
    }

    public List<Pedal> get20() {
        return pedalRepository.findTop20ByOrderByIdDesc();
    }
}
