package com.example.gmodscore.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class UsuarioModel implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private String login;

    @SerializedName("perfil")
    private String perfil;

    @SerializedName("ativo")
    private Boolean ativo;

    @SerializedName("criadoEm")
    private String criadoEm;

    
    public UsuarioModel() {}

    public UsuarioModel(Long id, String nome, String email, String login, String perfil, Boolean ativo, String criadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.perfil = perfil;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public String getCriadoEm() { return criadoEm; }
    public void setCriadoEm(String criadoEm) { this.criadoEm = criadoEm; }
}
