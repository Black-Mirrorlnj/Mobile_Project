package com.example.gmodscore.estatistica.api;

import com.example.gmodscore.estatistica.model.EstatisticaModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface EstatisticaApiService {

    @GET("estatisticas")
    Call<List<EstatisticaModel>> listarTodas();

    @GET("estatisticas/jogador/{jogadorId}")
    Call<EstatisticaModel> buscarPorJogador(@Path("jogadorId") Long jogadorId);

    @GET("estatisticas/{id}")
    Call<EstatisticaModel> buscarPorId(@Path("id") Long id);

    @POST("estatisticas")
    Call<EstatisticaModel> criar(@Body EstatisticaModel estatistica);

    @PUT("estatisticas/{id}")
    Call<EstatisticaModel> atualizar(@Path("id") Long id, @Body EstatisticaModel estatistica);

    @DELETE("estatisticas/{id}")
    Call<Void> deletar(@Path("id") Long id);
}
