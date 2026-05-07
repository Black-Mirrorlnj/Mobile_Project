package com.score.garrys.repository;

import com.score.garrys.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    
    Optional<Usuario> findByUsername(String username);

    
    Optional<Usuario> findByEmail(String email);

    
    boolean existsByUsername(String username);

    
    boolean existsByEmail(String email);

    
    List<Usuario> findByAtivo(boolean ativo);
}
