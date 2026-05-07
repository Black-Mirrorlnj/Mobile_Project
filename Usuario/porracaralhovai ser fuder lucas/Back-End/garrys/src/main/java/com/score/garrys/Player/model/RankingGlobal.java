package com.score.garrys.Player.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ranking_global")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingGlobal {

    @Id
    @Column(name = "jogador_id")
    private Long jogadorId;

    @Column(nullable = false)
    @Builder.Default
    private Integer pontos = 0;

    private Integer posicao;
}
