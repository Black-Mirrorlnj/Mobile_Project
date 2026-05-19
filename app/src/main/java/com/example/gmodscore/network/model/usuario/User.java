package com.example.gmodscore.network.model.usuario;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")       public long id;

    @SerializedName("nome")     public String nome;
    @SerializedName("email")    public String email;
    @SerializedName("login")    public String login;
    @SerializedName("perfil")   public String perfil;
    @SerializedName("ativo")    public boolean ativo;
    @SerializedName("criadoEm") public String criadoEm;

    @SerializedName("senha")    public String senha;

}
