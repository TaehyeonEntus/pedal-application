package entus.resourceServer.Service;

import entus.resourceServer.config.R2Config;
import entus.resourceServer.domain.Board;
import entus.resourceServer.domain.Pedal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class R2Service {
    private final R2Config r2Config;
    private final S3Presigner s3Presigner;
    private final BoardService boardService;
    private final PedalService pedalService;

    @Transactional
    public void setBoardImageUrl(Long boardId, String objectKey) {
        Board board = boardService.get(boardId);
        board.changeImageUrl(objectKey);
    }

    @Transactional
    public void setPedalImageUrl(Long pedalId, String objectKey) {
        Pedal pedal = pedalService.get(pedalId);
        pedal.changeImageUrl(objectKey);
    }

    // 업로드용 Presigned URL
    public String generatePresignedUploadUrl(String objectKey, Duration duration) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(r2Config.getBucketName())
                .key(objectKey)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(duration)
                .putObjectRequest(putObjectRequest)
                .build();

        return s3Presigner.presignPutObject(presignRequest).url().toString();
    }
}