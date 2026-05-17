package com.example.gmodscore.network.model.visitante;

import com.google.gson.annotations.SerializedName;

public class Visitante {
    @SerializedName("id_visitante")    public int id;
    @SerializedName("nome_usuario")    public String nomeUsuario;
    @SerializedName("horario_entrada") public String horarioEntrada;
    @SerializedName("horario_saida")   public String horarioSaida;
    @SerializedName("kills")           public int kills;
}