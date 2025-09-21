package entus.resourceServer.Service;

import entus.resourceServer.domain.Brand;
import entus.resourceServer.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Brand get(Long brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }

    public Long add(Brand brand) {
        return brandRepository.save(brand).getId();
    }

    public List<Brand> getAll(){
        return brandRepository.findAll();
    }
}