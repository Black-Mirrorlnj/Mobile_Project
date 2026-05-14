package com.example.gmodscore.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class EstatisticaModel implements Serializable {

    @SerializedName("id")
    private Long id;

    @SerializedName("jogadorId")
    private Long jogadorId;

    @SerializedName("kills")
    private Integer kills;

    @SerializedName("deaths")
    private Integer deaths;

    @SerializedName("dinheiro")
    private Integer dinheiro;

    @SerializedName("nivel")
    private Integer nivel;

    @SerializedName("experiencia")
    private Integer experiencia;

    @SerializedName("tempoJogado")
    private Integer tempoJogado;

    // --- Construtores ---
    public EstatisticaModel() {}

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJogadorId() { return jogadorId; }
    public void setJogadorId(Long jogadorId) { this.jogadorId = jogadorId; }

    public Integer getKills() { return kills; }
    public void setKills(Integer kills) { this.kills = kills; }

    public Integer getDeaths() { return deaths; }
    public void setDeaths(Integer deaths) { this.deaths = deaths; }

    public Integer getDinheiro() { return dinheiro; }
    public void setDinheiro(Integer dinheiro) { this.dinheiro = dinheiro; }

    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }

    public Integer getExperiencia() { return experiencia; }
    public void setExperiencia(Integer experiencia) { this.experiencia = experiencia; }

    public Integer getTempoJogado() { return tempoJogado; }
    public void setTempoJogado(Integer tempoJogado) { this.tempoJogado = tempoJogado; }

    /** K/D ratio calculado localmente */
    public float getKdRatio() {
        if (deaths == null || deaths == 0) return kills != null ? kills : 0f;
        return kills != null ? (float) kills / deaths : 0f;
    }
}
