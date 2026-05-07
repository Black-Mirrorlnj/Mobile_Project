package com.score.garrys.Player.mapper;

import com.score.garrys.Player.dto.Partida.PartidaRequestDTO;
import com.score.garrys.Player.dto.Partida.PartidaResponseDTO;
import com.score.garrys.Player.model.Partida;

public class PartidaMapper {

    private PartidaMapper() {
    }

    public static Partida toEntity(PartidaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Partida.builder()
                .mapa(dto.getMapa())
                .build();
    }

    public static PartidaResponseDTO toResponseDTO(Partida entity) {
        if (entity == null) {
            return null;
        }

        return PartidaResponseDTO.builder()
                .id(entity.getId())
                .mapa(entity.getMapa())
                .dataInicio(entity.getDataInicio())
                .dataFim(entity.getDataFim())
                .build();
    }
}