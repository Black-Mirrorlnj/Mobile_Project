package com.score.garrys.Visit.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class DTO {

    private int idVisitante;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Min(value = 0, message = "Kills não pode ser negativo")
    private int kills;

    public int getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
