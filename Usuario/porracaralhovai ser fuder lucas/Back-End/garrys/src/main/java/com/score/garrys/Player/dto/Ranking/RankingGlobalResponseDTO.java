package com.score.garrys.Player.dto.Ranking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankingGlobalResponseDTO {
    private Long jogadorId;
    private Integer pontos;
    private Integer posicao;
}