package com.score.garrys.Player.dto.Estatistica;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EstatisticaResponseDTO {
    private Long id;
    private Long jogadorId;
    private Integer kills;
    private Integer deaths;
    private Integer dinheiro;
    private Integer nivel;
    private Integer experiencia;
}