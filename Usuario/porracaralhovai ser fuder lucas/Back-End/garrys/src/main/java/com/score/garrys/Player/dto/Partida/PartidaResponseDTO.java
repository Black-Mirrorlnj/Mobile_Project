package com.score.garrys.Player.dto.Partida;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PartidaResponseDTO {
    private Long id;
    private String mapa;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}