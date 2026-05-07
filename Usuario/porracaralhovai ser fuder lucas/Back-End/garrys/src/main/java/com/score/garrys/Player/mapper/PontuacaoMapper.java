package com.score.garrys.Player.mapper;


import com.score.garrys.Player.dto.Pontuacao.PontuacaoRequestDTO;
import com.score.garrys.Player.dto.Pontuacao.PontuacaoResponseDTO;
import com.score.garrys.Player.model.Pontuacao;

public class PontuacaoMapper {

    private PontuacaoMapper() {
    }

    public static Pontuacao toEntity(PontuacaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Pontuacao.builder()
                .jogadorId(dto.getJogadorId())
                .partidaId(dto.getPartidaId())
                .scoreInicial(dto.getScoreInicial())
                .scoreFinal(dto.getScoreFinal())
                .build();
    }

    public static PontuacaoResponseDTO toResponseDTO(Pontuacao entity) {
        if (entity == null) {
            return null;
        }

        return PontuacaoResponseDTO.builder()
                .id(entity.getId())
                .jogadorId(entity.getJogadorId())
                .partidaId(entity.getPartidaId())
                .scoreInicial(entity.getScoreInicial())
                .scoreFinal(entity.getScoreFinal())
                .build();
    }
}