package com.example.gmodscore.network;

import com.example.gmodscore.network.model.player.Estatisticas;
import com.example.gmodscore.network.model.player.Jogador;
import com.example.gmodscore.network.model.player.Pontuacao;
import com.example.gmodscore.network.model.player.Ranking;
import com.example.gmodscore.network.model.partida.PartidaAtiva;
import com.example.gmodscore.network.model.servidor.Servidor;
import com.example.gmodscore.network.model.usuario.User;
import com.example.gmodscore.network.model.visitante.Visitante;
import com.example.gmodscore.network.model.usuario.LoginRequest;

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

    // ── USUÁRIOS ───────────────────────────────────────────
    @GET("usuarios")
    Call<List<User>> listarUsuarios();

    @GET("usuarios/{id}")
    Call<User> buscarUsuario(@Path("id") long id);

    @POST("usuarios")
    Call<User> criarUsuario(@Body User user);

    @PUT("usuarios/{id}")
    Call<User> atualizarUsuario(@Path("id") long id, @Body User user);

    @DELETE("usuarios/{id}")
    Call<Void> deletarUsuario(@Path("id") long id);

    @POST("usuarios/login")
    Call<User> login(@Body LoginRequest loginRequest);

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
