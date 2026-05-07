package com.score.garrys.Player.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalizarPontuacaoRequest {
    private Long jogadorId;
    private Long partidaId;
    private Integer scoreFinal;
    private Integer kills;
    private Integer deaths;
}