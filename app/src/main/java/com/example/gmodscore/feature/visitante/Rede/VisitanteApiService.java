package com.example.gmodscore.feature.visitante.Rede;

import com.example.gmodscore.feature.visitante.VisitanteModel.Visitante;
import com.example.gmodscore.feature.visitante.Ranking.VisitanteRanking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Endpoints do módulo Visitante.
 * Base URL: https://backend-production-db6e.up.railway.app/
 *
 * Endpoints do back-end (VisitanteController.java):
 *   GET    /visitantes                   - listar todos
 *   GET    /visitantes/ranking           - ranking por kills
 *   POST   /visitantes/entrada?nome=     - registrar entrada
 *   PUT    /visitantes/saida/{id}        - registrar saída + kills
 *   DELETE /visitantes/{id}              - remover visitante
 */
public interface VisitanteApiService {

    @GET("visitantes")
    Call<List<Visitante>> listarVisitantes();

    @GET("visitantes/ranking")
    Call<List<VisitanteRanking>> buscarRanking();

    @POST("visitantes/entrada")
    Call<Void> registrarEntrada(@Query("nome") String nome);

    @PUT("visitantes/saida/{id}")
    Call<Void> registrarSaida(@Path("id") int id, @Query("kills") int kills);

    @DELETE("visitantes/{id}")
    Call<Void> removerVisitante(@Path("id") int id);
}
