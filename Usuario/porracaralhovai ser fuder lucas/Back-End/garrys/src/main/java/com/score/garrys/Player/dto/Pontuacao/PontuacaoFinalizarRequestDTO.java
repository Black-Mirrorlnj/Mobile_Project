package com.score.garrys.Player.dto.Pontuacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PontuacaoFinalizarRequestDTO {
    private Long jogadorId;
    private Long partidaId;
    private Integer scoreFinal;
    private Integer kills;
    private Integer deaths;
}