package entus.resourceServer;

import entus.resourceServer.config.R2Config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@SpringBootTest
class ResourceServerApplicationTests {
	@MockitoBean
	R2Config r2Config;
	@MockitoBean
	S3Presigner s3Presigner;
	@Test
	void contextLoads() {
	}

}
