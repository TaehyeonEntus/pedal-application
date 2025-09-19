package entus.resourceServer.controller;

import entus.resourceServer.Service.PedalService;
import entus.resourceServer.Service.R2Service;
import entus.resourceServer.Service.UserService;
import entus.resourceServer.domain.Pedal;
import entus.resourceServer.domain.dto.request.AddPedalRequestDto;
import entus.resourceServer.domain.dto.request.UploadImageSuccessRequestDto;
import entus.resourceServer.domain.dto.response.AddPedalResponseDto;
import entus.resourceServer.domain.dto.response.PresignedUrlResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/pedal")
@RequiredArgsConstructor
public class PedalController {
    private final UserService userService;
    private final PedalService pedalService;
    private final R2Service r2Service;

    @PostMapping("/add")
    public AddPedalResponseDto addPedal(@RequestBody AddPedalRequestDto dto) {
        Long pedalId = pedalService.add(Pedal.create(dto.getName(), dto.getDescription(), dto.getBrand(), dto.getCategory()));
        return new AddPedalResponseDto(pedalId);
    }

    @PostMapping("/{pedalId}/upload")
    public PresignedUrlResponseDto uploadPedal(@PathVariable Long pedalId) {
        String objectKey = "pedal_" + pedalId + ".jpg";
        return new PresignedUrlResponseDto(r2Service.generatePresignedUploadUrl(objectKey, Duration.ofMinutes(5)));
    }

    @PostMapping("/{pedalId}/upload/success")
    public ResponseEntity<?> uploadImageSuccess(@PathVariable Long pedalId, @RequestBody UploadImageSuccessRequestDto dto) {
        r2Service.setPedalImageUrl(pedalId, dto.getObjectKey());
        return ResponseEntity.ok().body("upload success");
    }
}
