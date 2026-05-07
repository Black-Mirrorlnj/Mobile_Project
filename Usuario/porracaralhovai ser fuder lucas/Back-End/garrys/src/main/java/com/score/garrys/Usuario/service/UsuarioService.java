package com.score.garrys.service;

import com.score.garrys.model.Usuario;
import com.score.garrys.repository.UsuarioRepository;
import com.score.garrys.dto.UsuarioRequest;
import com.score.garrys.dto.UsuarioResponse;
import com.score.garrys.mapper.UsuarioMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    
    List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    
    UsuarioResponse buscarPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            return UsuarioMapper.toResponse(usuario);
        }

        return null;
    }

    
    UsuarioResponse criarUsuario(UsuarioRequest dto) {

        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já existe");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já existe");
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(salvo);
    }

    
    UsuarioResponse atualizarUsuario(int id, UsuarioRequest dto) {

        Usuario existente = usuarioRepository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        Usuario atualizado = UsuarioMapper.toEntityUpdate(dto, existente);
        Usuario salvo = usuarioRepository.save(atualizado);

        return UsuarioMapper.toResponse(salvo);
    }

    
    void deletarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
