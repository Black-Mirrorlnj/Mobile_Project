package com.example.gmodscore.network.model;

import com.google.gson.annotations.SerializedName;

public class Jogador {
    @SerializedName("id")
    public int id;

    @SerializedName("steam_id")
    public String steamId;

    @SerializedName("nome")
    public String nome;

    @SerializedName("ultimo_login")
    public String ultimoLogin;
}