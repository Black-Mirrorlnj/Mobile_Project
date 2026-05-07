package com.score.garrys.Player.controller;

import com.score.garrys.Player.dto.Jogador.JogadorRequestDTO;
import com.score.garrys.Player.dto.Jogador.JogadorResponseDTO;
import com.score.garrys.Player.mapper.JogadorMapper;
import com.score.garrys.Player.model.Jogador;
import com.score.garrys.Player.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public JogadorResponseDTO criar(@RequestBody JogadorRequestDTO dto) {
        Jogador jogador = JogadorMapper.toEntity(dto);
        Jogador salvo = jogadorService.salvar(jogador);
        return JogadorMapper.toResponseDTO(salvo);
    }

    @GetMapping
    public List<JogadorResponseDTO> listar() {
        return jogadorService.listar()
                .stream()
                .map(JogadorMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public JogadorResponseDTO buscarPorId(@PathVariable Long id) {
        Jogador jogador = jogadorService.buscarPorId(id);
        return JogadorMapper.toResponseDTO(jogador);
    }
}
