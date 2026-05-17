package com.example.gmodscore.network;

import com.example.gmodscore.network.model.player.Estatisticas;
import com.example.gmodscore.network.model.player.Jogador;
import com.example.gmodscore.network.model.player.Pontuacao;
import com.example.gmodscore.network.model.player.Ranking;
import com.example.gmodscore.network.model.partida.PartidaAtiva;
import com.example.gmodscore.network.model.servidor.Servidor;
import com.example.gmodscore.network.model.usuario.User;
import com.example.gmodscore.network.model.visitante.Visitante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ApiService {

    // ── JOGADORES ──────────────────────────────────────────
    @GET("jogadores")
    Call<List<Jogador>> listarJogadores();

    @GET("jogadores/{id}")
    Call<Jogador> buscarJogador(@Path("id") int id);

    @POST("jogadores")
    Call<Jogador> criarJogador(@Body Jogador jogador);

    // ── ESTATÍSTICAS ───────────────────────────────────────
    @GET("estatisticas")
    Call<List<Estatisticas>> listarEstatisticas();

    @GET("estatisticas/jogador/{id}")
    Call<Estatisticas> buscarEstatisticasPorJogador(@Path("id") int jogadorId);

    @PUT("estatisticas/jogador/{id}")
    Call<Estatisticas> atualizarEstatisticas(@Path("id") int jogadorId,
                                             @Body Estatisticas estatisticas);

    // ── RANKING ────────────────────────────────────────────
    @GET("ranking")
    Call<List<Ranking>> buscarRanking();

    // ── PONTUAÇÕES ─────────────────────────────────────────
    @GET("pontuacoes")
    Call<List<Pontuacao>> listarPontuacoes();

    @GET("pontuacoes/partida/{id}")
    Call<List<Pontuacao>> buscarPontuacoesPorPartida(@Path("id") int partidaId);

    @POST("pontuacoes")
    Call<Pontuacao> criarPontuacao(@Body Pontuacao pontuacao);

    @PUT("pontuacoes/finalizar")
    Call<Void> finalizarPontuacao(@Body Pontuacao pontuacao);

    // ── USERS ──────────────────────────────────────────────
    @GET("users")
    Call<List<User>> listarUsers();

    @GET("users/{id}")
    Call<User> buscarUser(@Path("id") int id);

    @POST("users")
    Call<User> criarUser(@Body User user);

    @PUT("users/{id}")
    Call<User> atualizarUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deletarUser(@Path("id") int id);

    // ── VISITANTES ─────────────────────────────────────────
    @GET("visitantes")
    Call<List<Visitante>> listarVisitantes();

    @POST("visitantes")
    Call<Visitante> registrarEntrada(@Body Visitante visitante);

    @PUT("visitantes/{id}/saida")
    Call<Visitante> registrarSaida(@Path("id") int id, @Body Visitante visitante);

    @DELETE("visitantes/{id}")
    Call<Void> removerVisitante(@Path("id") int id);

    // ── SERVIDORES ─────────────────────────────────────────
    @GET("servidores")
    Call<List<Servidor>> listarServidores();

    // ── MATCHES (partidas ativas) ──────────────────────────
    @GET("matches")
    Call<List<PartidaAtiva>> listarMatches();

    @POST("matches")
    Call<PartidaAtiva> criarMatch(@Body PartidaAtiva partida);
}
