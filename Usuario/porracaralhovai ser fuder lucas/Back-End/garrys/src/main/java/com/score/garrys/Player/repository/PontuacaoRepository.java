package com.score.garrys.Player.repository;

import com.score.garrys.Player.model.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {
    List<Pontuacao> findByPartidaId(Long partidaId);
    List<Pontuacao> findByJogadorId(Long jogadorId);
    Optional<Pontuacao> findByJogadorIdAndPartidaId(Long jogadorId, Long partidaId);
}