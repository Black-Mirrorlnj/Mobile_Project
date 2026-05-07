package com.score.garrys.Player.controller;


import com.score.garrys.Player.dto.Pontuacao.PontuacaoFinalizarRequestDTO;
import com.score.garrys.Player.dto.Pontuacao.PontuacaoRequestDTO;
import com.score.garrys.Player.dto.Pontuacao.PontuacaoResponseDTO;
import com.score.garrys.Player.mapper.PontuacaoMapper;
import com.score.garrys.Player.model.Pontuacao;
import com.score.garrys.Player.service.PontuacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontuacoes")
@RequiredArgsConstructor
public class PontuacaoController {

    private final PontuacaoService pontuacaoService;

    @PostMapping
    public PontuacaoResponseDTO criar(@RequestBody PontuacaoRequestDTO dto) {
        Pontuacao pontuacao = PontuacaoMapper.toEntity(dto);
        Pontuacao salva = pontuacaoService.criar(pontuacao);
        return PontuacaoMapper.toResponseDTO(salva);
    }

    @GetMapping
    public List<PontuacaoResponseDTO> listar() {
        return pontuacaoService.listar()
                .stream()
                .map(PontuacaoMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/partida/{partidaId}")
    public List<PontuacaoResponseDTO> listarPorPartida(@PathVariable Long partidaId) {
        return pontuacaoService.listarPorPartida(partidaId)
                .stream()
                .map(PontuacaoMapper::toResponseDTO)
                .toList();
    }

    @PutMapping("/finalizar")
    public PontuacaoResponseDTO finalizar(@RequestBody PontuacaoFinalizarRequestDTO dto) {
        Pontuacao pontuacao = pontuacaoService.finalizarPontuacao(
                dto.getJogadorId(),
                dto.getPartidaId(),
                dto.getScoreFinal(),
                dto.getKills(),
                dto.getDeaths()
        );

        return PontuacaoMapper.toResponseDTO(pontuacao);
    }
}

