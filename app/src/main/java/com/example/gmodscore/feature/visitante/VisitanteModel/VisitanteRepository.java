package com.example.gmodscore.feature.visitante.VisitanteModel;

import androidx.lifecycle.MutableLiveData;

import com.example.gmodscore.feature.visitante.Rede.RetrofitClient;
import com.example.gmodscore.feature.visitante.Rede.VisitanteApiService;
import com.example.gmodscore.feature.visitante.Ranking.VisitanteRanking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitanteRepository {

    private VisitanteApiService apiService;

    public VisitanteRepository() {
        this.apiService = RetrofitClient.getApiService();
    }

    public void listarVisitantes(MutableLiveData<List<Visitante>> visitantes, MutableLiveData<String> erro) {
        apiService.listarVisitantes().enqueue(new Callback<List<Visitante>>() {
            @Override
            public void onResponse(Call<List<Visitante>> call, Response<List<Visitante>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visitantes.setValue(response.body());
                } else {
                    erro.setValue("Erro ao carregar visitantes: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Visitante>> call, Throwable t) {
                erro.setValue("Falha de conexão: " + t.getMessage());
            }
        });
    }

    public void registrarEntrada(String nome, MutableLiveData<Boolean> sucesso, MutableLiveData<String> erro) {
        apiService.registrarEntrada(nome).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    sucesso.setValue(true);
                } else {
                    erro.setValue("Erro ao registrar entrada: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                erro.setValue("Falha de conexão: " + t.getMessage());
            }
        });
    }

    public void registrarSaida(int id, int kills, MutableLiveData<Boolean> sucesso, MutableLiveData<String> erro) {
        apiService.registrarSaida(id, kills).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    sucesso.setValue(true);
                } else {
                    erro.setValue("Erro ao registrar saída: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                erro.setValue("Falha de conexão: " + t.getMessage());
            }
        });
    }

    public void removerVisitante(int id, MutableLiveData<Boolean> sucesso, MutableLiveData<String> erro) {
        apiService.removerVisitante(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    sucesso.setValue(true);
                } else {
                    erro.setValue("Erro ao remover visitante: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                erro.setValue("Falha de conexão: " + t.getMessage());
            }
        });
    }

    public void buscarRanking(MutableLiveData<List<VisitanteRanking>> ranking, MutableLiveData<String> erro) {
        apiService.buscarRanking().enqueue(new Callback<List<VisitanteRanking>>() {
            @Override
            public void onResponse(Call<List<VisitanteRanking>> call, Response<List<VisitanteRanking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ranking.setValue(response.body());
                } else {
                    erro.setValue("Erro ao carregar ranking: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<VisitanteRanking>> call, Throwable t) {
                erro.setValue("Falha de conexão: " + t.getMessage());
            }
        });
    }
}
