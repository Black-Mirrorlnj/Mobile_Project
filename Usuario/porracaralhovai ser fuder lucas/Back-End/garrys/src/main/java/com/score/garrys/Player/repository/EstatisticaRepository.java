package com.score.garrys.Player.repository;



import com.score.garrys.Player.model.Estatistica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {
    Optional<Estatistica> findByJogadorId(Long jogadorId);
}