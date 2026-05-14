package com.example.gmodscore.feature.visitante.Ranking;

import com.google.gson.annotations.SerializedName;

public class VisitanteRanking {

    @SerializedName("id")
    public int id;

    @SerializedName("nome")
    public String nome;

    @SerializedName("kills")
    public int kills;

    @SerializedName("posicao")
    public int posicao;

    public VisitanteRanking() {}

    public VisitanteRanking(int id, String nome, int kills, int posicao) {
        this.id = id;
        this.nome = nome;
        this.kills = kills;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getKills() {
        return kills;
    }

    public int getPosicao() {
        return posicao;
    }
}
