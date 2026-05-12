package com.example.gmodscore.network.model;

import com.google.gson.annotations.SerializedName;

public class Ranking {
    @SerializedName("jogador_id")
    public int jogadorId;

    @SerializedName("pontos")
    public int pontos;

    @SerializedName("posicao")
    public int posicao;

    // Preenchido pelo join com a tabela jogadores
    @SerializedName("nome")
    public String nome;

    @SerializedName("steam_id")
    public String steamId;
}