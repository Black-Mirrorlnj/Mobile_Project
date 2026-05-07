package com.score.garrys.mapper;

import com.score.garrys.model.Usuario;
import com.score.garrys.dto.UsuarioRequest;
import com.score.garrys.dto.UsuarioResponse;

class UsuarioMapper {

    
    static Usuario toEntity(UsuarioRequest dto) {

        Usuario usuario = new Usuario();

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());

        return usuario;
    }

    
    static Usuario toEntityUpdate(UsuarioRequest dto, Usuario existente) {

        existente.setUsername(dto.getUsername());
        existente.setPassword(dto.getPassword());
        existente.setEmail(dto.getEmail());

        return existente;
    }

    // Entity → Response (retorno seguro)
    static UsuarioResponse toResponse(Usuario usuario) {

        UsuarioResponse response = new UsuarioResponse();

        response.setUserId(usuario.getUserId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setAtivo(usuario.isAtivo());
        response.setCreatedAt(usuario.getCreatedAt());

        return response;
    }
}
