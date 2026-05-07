package com.score.garrys.Player.repository;

import com.score.garrys.Player.model.RankingGlobal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingGlobalRepository extends JpaRepository<RankingGlobal, Long> {
    List<RankingGlobal> findAllByOrderByPontosDesc();
}