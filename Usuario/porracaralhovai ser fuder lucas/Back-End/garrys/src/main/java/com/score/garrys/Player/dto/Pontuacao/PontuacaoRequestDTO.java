package com.score.garrys.Player.dto.Pontuacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PontuacaoRequestDTO {
    private Long jogadorId;
    private Long partidaId;
    private Integer scoreInicial;
    private Integer scoreFinal;
}