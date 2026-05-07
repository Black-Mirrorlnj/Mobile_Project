package com.score.garrys.Player.mapper;

import com.score.garrys.Player.dto.Jogador.JogadorRequestDTO;
import com.score.garrys.Player.dto.Jogador.JogadorResponseDTO;
import com.score.garrys.Player.model.Jogador;

public class JogadorMapper {

    private JogadorMapper() {
    }

    public static Jogador toEntity(JogadorRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Jogador.builder()
                .steamId(dto.getSteamId())
                .nome(dto.getNome())
                .build();
    }

    public static JogadorResponseDTO toResponseDTO(Jogador entity) {
        if (entity == null) {
            return null;
        }

        return JogadorResponseDTO.builder()
                .id(entity.getId())
                .steamId(entity.getSteamId())
                .nome(entity.getNome())
                .ultimoLogin(entity.getUltimoLogin())
                .criadoEm(entity.getCriadoEm())
                .build();
    }
}