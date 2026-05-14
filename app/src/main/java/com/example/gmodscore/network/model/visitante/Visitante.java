package com.example.gmodscore.network.model.visitante;

import com.google.gson.annotations.SerializedName;

public class Visitante {

    @SerializedName("id")
    public int id;

    @SerializedName("nome")
    public String nome;

    @SerializedName("entrada")
    public String entrada;

    @SerializedName("saida")
    public String saida;

    @SerializedName("kills")
    public int kills;
}