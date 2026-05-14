package com.example.gmodscore.util.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferences {

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionPreferences(Context context) {
        prefs  = context.getSharedPreferences(SessionKeys.PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void salvar(Long id, String nome, String login, String perfil) {
        editor.putBoolean(SessionKeys.KEY_LOGADO, true);
        editor.putLong   (SessionKeys.KEY_ID,     id);
        editor.putString (SessionKeys.KEY_NOME,   nome);
        editor.putString (SessionKeys.KEY_LOGIN,  login);
        editor.putString (SessionKeys.KEY_PERFIL, perfil);
        editor.apply();
    }

    public void limpar() {
        editor.clear();
        editor.apply();
    }

    public boolean estaLogado()  { return prefs.getBoolean(SessionKeys.KEY_LOGADO, false); }
    public Long    getId()       { return prefs.getLong   (SessionKeys.KEY_ID,     -1L);   }
    public String  getNome()     { return prefs.getString (SessionKeys.KEY_NOME,   "");    }
    public String  getLogin()    { return prefs.getString (SessionKeys.KEY_LOGIN,  "");    }
    public String  getPerfil()   { return prefs.getString (SessionKeys.KEY_PERFIL, "");    }
}
