package com.example.gmodscore.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {

    private static final String PREF_NAME    = "gmod_session";
    private static final String KEY_ID       = "usuario_id";
    private static final String KEY_NOME     = "usuario_nome";
    private static final String KEY_LOGIN    = "usuario_login";
    private static final String KEY_PERFIL   = "usuario_perfil";
    private static final String KEY_LOGADO   = "logado";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs  = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

  
    public void salvarSessao(Long id, String nome, String login, String perfil) {
        editor.putBoolean(KEY_LOGADO, true);
        editor.putLong(KEY_ID, id);
        editor.putString(KEY_NOME, nome);
        editor.putString(KEY_LOGIN, login);
        editor.putString(KEY_PERFIL, perfil);
        editor.apply();
    }

    public boolean estaLogado()      { return prefs.getBoolean(KEY_LOGADO, false); }
    public Long getUsuarioId()       { return prefs.getLong(KEY_ID, -1L); }
    public String getUsuarioNome()   { return prefs.getString(KEY_NOME, ""); }
    public String getUsuarioLogin()  { return prefs.getString(KEY_LOGIN, ""); }
    public String getUsuarioPerfil() { return prefs.getString(KEY_PERFIL, ""); }

   
    public void encerrarSessao() {
        editor.clear();
        editor.apply();
    }
}
