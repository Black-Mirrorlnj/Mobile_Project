package com.example.gmodscore.network.model.player;

import com.google.gson.annotations.SerializedName;

public class Pontuacao {
    @SerializedName("id")
    public int id;

    @SerializedName("jogador_id")
    public int jogadorId;

    @SerializedName("partida_id")
    public int partidaId;

    @SerializedName("score_inicial")
    public int scoreInicial;

    @SerializedName("score_final")
    public int scoreFinal;
}
