package com.score.garrys.Player.service;



import com.score.garrys.Player.model.Estatistica;
import com.score.garrys.Player.model.Jogador;
import com.score.garrys.Player.model.RankingGlobal;
import com.score.garrys.Player.repository.EstatisticaRepository;
import com.score.garrys.Player.repository.JogadorRepository;
import com.score.garrys.Player.repository.RankingGlobalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final EstatisticaRepository estatisticaRepository;
    private final RankingGlobalRepository rankingGlobalRepository;

    public Jogador criar(Jogador jogador) {
        jogador.setUltimoLogin(LocalDateTime.now());
        Jogador salvo = jogadorRepository.save(jogador);

        estatisticaRepository.findByJogadorId(salvo.getId())
                .orElseGet(() -> estatisticaRepository.save(
                        Estatistica.builder()
                                .jogadorId(salvo.getId())
                                .kills(0)
                                .deaths(0)
                                .dinheiro(0)
                                .nivel(1)
                                .experiencia(0)
                                .build()
                ));

        rankingGlobalRepository.findById(salvo.getId())
                .orElseGet(() -> rankingGlobalRepository.save(
                        RankingGlobal.builder()
                                .jogadorId(salvo.getId())
                                .pontos(0)
                                .posicao(null)
                                .build()
                ));

        return salvo;
    }

    public List<Jogador> listar() {
        return jogadorRepository.findAll();
    }

    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }
    public Jogador salvar(Jogador jogador) {
    return jogadorRepository.save(jogador);
}
    public Jogador loginPorSteamId(String steamId, String nome) {
        return jogadorRepository.findBySteamId(steamId)
                .map(jogador -> {
                    jogador.setNome(nome);
                    jogador.setUltimoLogin(LocalDateTime.now());
                    return jogadorRepository.save(jogador);
                })
                .orElseGet(() -> criar(
                        Jogador.builder()
                                .steamId(steamId)
                                .nome(nome)
                                .ultimoLogin(LocalDateTime.now())
                                .build()
                ));
    }
    


}    

