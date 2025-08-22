package entus.resourceServer.Service;

import entus.resourceServer.domain.Category;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.repository.PedalRepository;
import lombok.RequiredArgsConstructor;
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

    public List<Pedal> findAll() {
        return pedalRepository.findAll();
    }

    public List<Pedal> findByCategory(Category category) {
        List<Pedal> pedals = new ArrayList<>();

        // 최하위 카테고리, 실제 페달인 경우
        if (category.getChildren().isEmpty())
            pedals.addAll(pedalRepository.findByCategory(category));
        // 하위 카테고리가 있는 경우, 하위 탐색
        else
            for (Category child : category.getChildren())
                pedals.addAll(findByCategory(child));

        return pedals;
    }
}
