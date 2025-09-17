package entus.resourceServer.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
@Getter
public class R2Config {
    @Value("${R2_ENDPOINT}")
    private String endpoint;

    @Value("${R2_ACCESS_KEY}")
    private String accessKey;

    @Value("${R2_SECRET_KEY}")
    private String secretKey;

    @Value("${R2_BUCKET_NAME}")
    private String bucketName;

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .region(Region.of("auto"))
                .build();
    }
}
