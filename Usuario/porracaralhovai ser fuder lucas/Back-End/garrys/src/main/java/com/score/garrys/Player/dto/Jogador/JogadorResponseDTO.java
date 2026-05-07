package com.score.garrys.Player.dto.Jogador;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class JogadorResponseDTO {
    private Long id;
    private String steamId;
    private String nome;
    private LocalDateTime ultimoLogin;
    private LocalDateTime criadoEm;
}