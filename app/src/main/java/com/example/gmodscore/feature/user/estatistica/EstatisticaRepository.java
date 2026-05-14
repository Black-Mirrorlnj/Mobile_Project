package com.example.gmodscore.estatistica.repository;

import com.example.gmodscore.estatistica.model.EstatisticaModel;
import com.example.gmodscore.retrofit.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstatisticaRepository {

    private final EstatisticaApiService apiService;

    public EstatisticaRepository() {
        this.apiService = RetrofitClient.getInstance()
                .create(EstatisticaApiService.class);
    }

    public interface Callback1<T> {
        void onSuccess(T data);
        void onError(String mensagem);
    }

    public void buscarPorJogador(Long jogadorId, Callback1<EstatisticaModel> callback) {
        apiService.buscarPorJogador(jogadorId).enqueue(new Callback<EstatisticaModel>() {
            @Override
            public void onResponse(Call<EstatisticaModel> call, Response<EstatisticaModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Estatística não encontrada");
                }
            }

            @Override
            public void onFailure(Call<EstatisticaModel> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void atualizar(Long id, EstatisticaModel estatistica, Callback1<EstatisticaModel> callback) {
        apiService.atualizar(id, estatistica).enqueue(new Callback<EstatisticaModel>() {
            @Override
            public void onResponse(Call<EstatisticaModel> call, Response<EstatisticaModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Falha ao atualizar");
                }
            }

            @Override
            public void onFailure(Call<EstatisticaModel> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }
}
