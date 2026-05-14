package com.example.gmodscore.feature.visitante.VisitanteModel;

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

    public Visitante() {}

    public Visitante(int id, String nome, String entrada, String saida, int kills) {
        this.id = id;
        this.nome = nome;
        this.entrada = entrada;
        this.saida = saida;
        this.kills = kills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
