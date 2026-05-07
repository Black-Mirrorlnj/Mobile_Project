package com.score.garrys.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    
    Usuario() {
    }

    
    Usuario(int userId, String username, String password, String email,
            boolean ativo, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ativo = ativo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.ativo = true;
    }

    
    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    

    int getUserId() {
        return userId;
    }

    void setUserId(int userId) {
        this.userId = userId;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    boolean isAtivo() {
        return ativo;
    }

    void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
