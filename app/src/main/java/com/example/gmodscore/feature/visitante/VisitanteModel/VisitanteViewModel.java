package com.example.gmodscore.feature.visitante.VisitanteModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gmodscore.feature.visitante.Ranking.VisitanteRanking;

import java.util.List;

public class VisitanteViewModel extends ViewModel {

    private VisitanteRepository repository;
    private MutableLiveData<List<Visitante>> visitantes;
    private MutableLiveData<Boolean> sucesso;
    private MutableLiveData<String> erro;
    private MutableLiveData<List<VisitanteRanking>> ranking;

    public VisitanteViewModel() {
        repository = new VisitanteRepository();
        visitantes = new MutableLiveData<>();
        sucesso = new MutableLiveData<>();
        erro = new MutableLiveData<>();
        ranking = new MutableLiveData<>();
    }

    public LiveData<List<Visitante>> getVisitantes() {
        return visitantes;
    }

    public LiveData<Boolean> getSucesso() {
        return sucesso;
    }

    public LiveData<String> getErro() {
        return erro;
    }

    public LiveData<List<VisitanteRanking>> getRanking() {
        return ranking;
    }

    public void listarVisitantes() {
        repository.listarVisitantes(visitantes, erro);
    }

    public void registrarEntrada(String nome) {
        repository.registrarEntrada(nome, sucesso, erro);
    }

    public void registrarSaida(int id, int kills) {
        repository.registrarSaida(id, kills, sucesso, erro);
    }

    public void removerVisitante(int id) {
        repository.removerVisitante(id, sucesso, erro);
    }

    public void buscarRanking() {
        repository.buscarRanking(ranking, erro);
    }
}
