package com.example.gmodscore.network.model.player;

import com.google.gson.annotations.SerializedName;

public class Estatisticas {
    @SerializedName("id")
    public int id;

    @SerializedName("jogador_id")
    public int jogadorId;

    @SerializedName("kills")
    public int kills;

    @SerializedName("deaths")
    public int deaths;

    @SerializedName("dinheiro")
    public int dinheiro;

    @SerializedName("nivel")
    public int nivel;

    @SerializedName("experiencia")
    public int experiencia;

    @SerializedName("tempo_jogado")
    public int tempoJogado;
}