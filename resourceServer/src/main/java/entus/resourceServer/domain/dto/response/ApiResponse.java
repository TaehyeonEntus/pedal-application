package entus.resourceServer.domain.dto.response;

import lombok.Data;

@Data
public class ApiResponse {
    String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}
