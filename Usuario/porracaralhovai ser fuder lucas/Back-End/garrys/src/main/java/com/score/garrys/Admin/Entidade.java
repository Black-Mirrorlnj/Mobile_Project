package com.score.garrys.Admin.Entidade;

import java.time.LocalDateTime;

public class Entidade {

    private int idAdmin;
    private String username;
    private String password;
    private String email;
    private int nivelAcesso;
    private String status;
    private LocalDateTime criadoEm;
    private LocalDateTime ultimoLogin;

    public Administrador() {}

    public Administrador(String username, String password, String email, int nivelAcesso) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nivelAcesso = nivelAcesso;
        this.status = "ativo";
        this.criadoEm = LocalDateTime.now();
    }

    public boolean isAtivo() {
        return "ativo".equalsIgnoreCase(this.status);
    }

    public boolean temPermissao(int nivelNecessario) {
        return this.nivelAcesso >= nivelNecessario;
    }

    public void registrarLogin() {
        this.ultimoLogin = LocalDateTime.now();
    }

    public void suspender() {
        this.status = "suspenso";
    }

    public void ativar() {
        this.status = "ativo";
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
}
