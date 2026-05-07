package com.score.garrys.Player.mapper;

import com.score.garrys.Player.dto.Ranking.RankingGlobalResponseDTO;
import com.score.garrys.Player.model.RankingGlobal;

public class RankingGlobalMapper {

    private RankingGlobalMapper() {
    }

    public static RankingGlobalResponseDTO toResponseDTO(RankingGlobal entity) {
        if (entity == null) {
            return null;
        }

        return RankingGlobalResponseDTO.builder()
                .jogadorId(entity.getJogadorId())
                .pontos(entity.getPontos())
                .posicao(entity.getPosicao())
                .build();
    }
}