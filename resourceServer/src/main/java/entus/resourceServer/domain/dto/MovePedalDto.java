package entus.resourceServer.domain.dto;

import lombok.Data;

@Data
public class MovePedalDto {
    private long pedalId;
    private int destination;
}
