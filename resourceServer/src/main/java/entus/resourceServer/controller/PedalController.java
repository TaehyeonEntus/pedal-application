package entus.resourceServer.controller;

import entus.resourceServer.Service.PedalService;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.request.AddPedalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedal")
@RequiredArgsConstructor
public class PedalController {
    private final PedalService pedalService;
    @PostMapping("/add")
    public ResponseEntity<?> addPedal(@RequestBody AddPedalRequestDto dto) {
        Pedal pedal = Pedal.create(dto.getName(),dto.getDescription(),dto.getCategory());
        pedal.changeImageUrl(dto.getImageUrl());
        Long pedalId = pedalService.add(pedal);

        return ResponseEntity.ok().body(pedalId);
    }
}
