package com.score.garrys.Player.repository;

import com.score.garrys.Player.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findBySteamId(String steamId);
}