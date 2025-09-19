package entus.resourceServer.controller;

import entus.resourceServer.Service.PedalService;
import entus.resourceServer.Service.R2Service;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.request.AddPedalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/pedal")
@RequiredArgsConstructor
public class PedalController {
    private final PedalService pedalService;
    private final R2Service r2Service;

    @PostMapping("/add")
    public ResponseEntity<?> addPedal(@RequestBody AddPedalRequestDto dto) {
        Long pedalId = pedalService.add(Pedal.create(dto.getName(), dto.getDescription(), dto.getBrand(), dto.getCategory()));

        return ResponseEntity.ok().body(pedalId);
    }

    @PostMapping("/{pedalId}/upload")
    public ResponseEntity<?> uploadPedal(@PathVariable Long pedalId) {
        String objectKey = "pedals/" + "pedal" + "_" + pedalId;
        r2Service.setPedalImageUrl(pedalId, objectKey);
        return ResponseEntity.ok(Map.of("uploadUrl", r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5))));
    }
}
