package com.example.gmodscore.usuario.repository;

import com.example.gmodscore.usuario.api.UsuarioApiService;
import com.example.gmodscore.usuario.model.UsuarioModel;
import com.example.gmodscore.retrofit.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {

    private final UsuarioApiService apiService;

    public UsuarioRepository() {
        this.apiService = RetrofitClient.getInstance()
                .create(UsuarioApiService.class);
    }

    public interface Callback1<T> {
        void onSuccess(T data);
        void onError(String mensagem);
    }

    public void buscarPorId(Long id, Callback1<UsuarioModel> callback) {
        apiService.buscarPorId(id).enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Usuário não encontrado");
                }
            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void listarTodos(Callback1<List<UsuarioModel>> callback) {
        apiService.listarTodos().enqueue(new Callback<List<UsuarioModel>>() {
            @Override
            public void onResponse(Call<List<UsuarioModel>> call, Response<List<UsuarioModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Nenhum usuário encontrado");
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioModel>> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void atualizar(Long id, UsuarioModel usuario, Callback1<UsuarioModel> callback) {
        apiService.atualizar(id, usuario).enqueue(new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Falha ao atualizar usuário");
                }
            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void deletar(Long id, Callback1<Void> callback) {
        apiService.deletar(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Falha ao deletar usuário");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }
}
