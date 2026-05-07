package com.score.garrys.Player.controller;


import com.score.garrys.Player.dto.Estatistica.EstatisticaRequestDTO;
import com.score.garrys.Player.dto.Estatistica.EstatisticaResponseDTO;
import com.score.garrys.Player.mapper.EstatisticaMapper;
import com.score.garrys.Player.model.Estatistica;
import com.score.garrys.Player.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    public List<EstatisticaResponseDTO> listar() {
        return estatisticaService.listar()
                .stream()
                .map(EstatisticaMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/jogador/{jogadorId}")
    public EstatisticaResponseDTO buscarPorJogadorId(@PathVariable Long jogadorId) {
        Estatistica estatistica = estatisticaService.buscarPorJogadorId(jogadorId);
        return EstatisticaMapper.toResponseDTO(estatistica);
    }

    @PutMapping("/jogador/{jogadorId}")
    public EstatisticaResponseDTO atualizar(@PathVariable Long jogadorId,
                                            @RequestBody EstatisticaRequestDTO dto) {
        Estatistica estatistica = EstatisticaMapper.toEntity(dto);
        Estatistica atualizada = estatisticaService.atualizar(jogadorId, estatistica);
        return EstatisticaMapper.toResponseDTO(atualizada);
    }
}