package com.score.garrys.Visit.Service;

import com.score.garrys.Visit.Model.Visitante;
import com.score.garrys.Visit.Repository.VisitanteRepository;

import java.util.List;

public class VisitanteService {

    private final VisitanteRepository repository = new VisitanteRepository();

    public void registrarEntrada(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new RuntimeException("Nome deve ter pelo menos 3 caracteres");
        }

        repository.registrarEntrada(nome.trim());
    }

    public void registrarSaida(int id, int kills) {
        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        if (kills < 0) {
            throw new RuntimeException("Kills não pode ser negativo");
        }

        repository.registrarSaida(id, kills);
    }

    public void remover(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID inválido");
        }

        repository.remover(id);
    }

    public List<Visitante> listar() {
        List<Visitante> lista = repository.listar();

        if (lista.isEmpty()) {
            throw new RuntimeException("Nenhum visitante encontrado");
        }

        return lista;
    }

    public List<Visitante> rankingKills() {
        return repository.ranking();
    }
}
