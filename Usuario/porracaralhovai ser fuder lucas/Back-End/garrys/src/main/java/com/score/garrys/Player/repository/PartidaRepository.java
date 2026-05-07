package com.score.garrys.Player.repository;

import com.score.garrys.Player.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
}