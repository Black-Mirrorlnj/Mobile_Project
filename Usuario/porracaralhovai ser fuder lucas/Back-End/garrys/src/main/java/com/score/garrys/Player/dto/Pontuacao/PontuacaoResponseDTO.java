package com.score.garrys.Player.dto.Pontuacao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PontuacaoResponseDTO {
    private Long id;
    private Long jogadorId;
    private Long partidaId;
    private Integer scoreInicial;
    private Integer scoreFinal;
}