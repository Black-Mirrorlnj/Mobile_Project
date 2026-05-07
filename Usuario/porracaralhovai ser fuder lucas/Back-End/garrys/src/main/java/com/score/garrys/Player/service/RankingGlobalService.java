package com.score.garrys.Player.service;

import com.score.garrys.Player.model.RankingGlobal;
import com.score.garrys.Player.repository.RankingGlobalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingGlobalService {

    private final RankingGlobalRepository rankingGlobalRepository;

    public List<RankingGlobal> listar() {
        return rankingGlobalRepository.findAllByOrderByPontosDesc();
    }
}