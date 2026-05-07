package com.score.garrys.Player.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estatisticas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estatistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jogador_id", nullable = false, unique = true)
    private Long jogadorId;

    @Column(nullable = false)
    @Builder.Default
    private Integer kills = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer deaths = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer dinheiro = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer nivel = 1;

    @Column(nullable = false)
    @Builder.Default
    private Integer experiencia = 0;
}