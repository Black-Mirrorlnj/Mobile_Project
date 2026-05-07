package com.score.garrys.dto;

import java.time.LocalDateTime;

class UsuarioResponse {

    // 🔧 Atributos
    private int userId;
    private String username;
    private String email;
    private boolean ativo;
    private LocalDateTime createdAt;

    //  Construtor vazio
    UsuarioResponse() {
    }

    //  Construtor completo
    UsuarioResponse(int userId, String username, String email,
                    boolean ativo, LocalDateTime createdAt) {

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.ativo = ativo;
        this.createdAt = createdAt;
    }

    //  Getter e Setter - userId
    int getUserId() {
        return userId;
    }

    void setUserId(int userId) {
        this.userId = userId;
    }

    //  Getter e Setter - username
    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    //  Getter e Setter - email
    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    //  Getter e Setter - ativo
    boolean isAtivo() {
        return ativo;
    }

    void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    //  Getter e Setter - createdAt
    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
