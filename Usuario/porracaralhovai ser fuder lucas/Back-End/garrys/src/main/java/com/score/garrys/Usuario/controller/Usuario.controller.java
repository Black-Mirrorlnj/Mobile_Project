package com.score.garrys.controller;

import com.score.garrys.model.Usuario;
import com.score.garrys.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    Usuario buscarPorId(@PathVariable int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    Usuario atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {

        Usuario existente = usuarioRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setUsername(usuario.getUsername());
            existente.setPassword(usuario.getPassword());
            existente.setEmail(usuario.getEmail());
            existente.setAtivo(usuario.isAtivo());

            return usuarioRepository.save(existente);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    void deletarUsuario(@PathVariable int id) {
        usuarioRepository.deleteById(id);
    }
}
