package entus.resourceServer.controller;

import entus.resourceServer.Service.BrandService;
import entus.resourceServer.Service.CategoryService;
import entus.resourceServer.Service.PedalService;
import entus.resourceServer.Service.R2Service;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.request.AddPedalRequestDto;
import entus.resourceServer.domain.dto.request.UploadImageSuccessRequestDto;
import entus.resourceServer.domain.dto.response.AddPedalResponseDto;
import entus.resourceServer.domain.dto.response.PresignedUrlResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/pedal")
@RequiredArgsConstructor
public class PedalController {
    private final PedalService pedalService;
    private final R2Service r2Service;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public AddPedalResponseDto addPedal(@RequestBody AddPedalRequestDto dto) {
        return new AddPedalResponseDto(pedalService.add(Pedal.create(dto.getName(), dto.getDescription(), brandService.get(dto.getBrandId()), categoryService.get(dto.getCategoryId()))));
    }

    @PostMapping("/{pedalId}/upload")
    public PresignedUrlResponseDto uploadPedal(@PathVariable Long pedalId) {
        String objectKey = "pedal_" + pedalId + ".jpg";
        return new PresignedUrlResponseDto(r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5)), objectKey);
    }

    @PostMapping("/{pedalId}/upload/success")
    public ResponseEntity<?> uploadImageSuccess(@PathVariable Long pedalId, @RequestBody UploadImageSuccessRequestDto dto) {
        r2Service.setPedalImageUrl(pedalId, dto.getObjectKey());
        return ResponseEntity.ok().body("upload success");
    }
}
