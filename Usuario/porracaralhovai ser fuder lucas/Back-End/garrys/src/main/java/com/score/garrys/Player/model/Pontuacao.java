package com.score.garrys.Player.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pontuacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jogador_id", nullable = false)
    private Long jogadorId;

    @Column(name = "partida_id", nullable = false)
    private Long partidaId;

    @Column(name = "score_inicial", nullable = false)
    @Builder.Default
    private Integer scoreInicial = 0;

    @Column(name = "score_final", nullable = false)
    @Builder.Default
    private Integer scoreFinal = 0;
}