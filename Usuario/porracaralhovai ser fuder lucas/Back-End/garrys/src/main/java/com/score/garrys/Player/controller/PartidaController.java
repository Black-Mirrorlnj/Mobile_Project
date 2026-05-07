package com.score.garrys.Player.controller;

import com.score.garrys.Player.dto.Partida.PartidaRequestDTO;
import com.score.garrys.Player.dto.Partida.PartidaResponseDTO;
import com.score.garrys.Player.mapper.PartidaMapper;
import com.score.garrys.Player.model.Partida;
import com.score.garrys.Player.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;

    @PostMapping
    public PartidaResponseDTO criar(@RequestBody PartidaRequestDTO dto) {
        Partida partida = PartidaMapper.toEntity(dto);
        Partida salva = partidaService.criar(partida.getMapa());
        return PartidaMapper.toResponseDTO(salva);
    }

    @GetMapping
    public List<PartidaResponseDTO> listar() {
        return partidaService.listar()
                .stream()
                .map(PartidaMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PartidaResponseDTO buscarPorId(@PathVariable Long id) {
        Partida partida = partidaService.buscarPorId(id);
        return PartidaMapper.toResponseDTO(partida);
    }

    @PutMapping("/{id}/finalizar")
    public PartidaResponseDTO finalizar(@PathVariable Long id) {
        Partida partida = partidaService.finalizar(id);
        return PartidaMapper.toResponseDTO(partida);
    }
}