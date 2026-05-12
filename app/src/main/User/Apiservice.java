package com.example.gmodscore.api;

import com.example.gmodscore.model.EstatisticaModel;
import com.example.gmodscore.model.UsuarioModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiService {

    
    @POST("usuarios/login")
    Call<UsuarioModel> login(@Body Map<String, String> credenciais);

    
    @POST("usuarios")
    Call<UsuarioModel> criarUsuario(@Body Map<String, Object> dto);

    
    @GET("usuarios")
    Call<List<UsuarioModel>> listarUsuarios();

    
    @GET("usuarios/{id}")
    Call<UsuarioModel> buscarUsuarioPorId(@Path("id") Long id);

    
    @PUT("usuarios/{id}")
    Call<UsuarioModel> atualizarUsuario(@Path("id") Long id, @Body Map<String, Object> dto);

    
    @DELETE("usuarios/{id}")
    Call<Void> deletarUsuario(@Path("id") Long id);

   
     * Retorna estatísticas de kills, deaths, nível, etc.
     */
    @GET("usuarios/{id}/estatisticas")
    Call<EstatisticaModel> buscarEstatisticas(@Path("id") Long id);
}
