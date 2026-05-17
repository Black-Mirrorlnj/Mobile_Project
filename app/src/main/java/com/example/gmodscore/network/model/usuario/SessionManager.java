package com.example.gmodscore.network.model.usuario;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME  = "gmodscore_session";
    private static final String KEY_USER_ID   = "userId";
    private static final String KEY_USERNAME  = "username";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    private final SharedPreferences prefs;

    public SessionManager(Context ctx) {
        prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void salvarSessao(int userId, String username) {
        prefs.edit()
                .putInt(KEY_USER_ID, userId)
                .putString(KEY_USERNAME, username)
                .putBoolean(KEY_LOGGED_IN, true)
                .apply();
    }

    public boolean estaLogado()    { return prefs.getBoolean(KEY_LOGGED_IN, false); }
    public int getUserId()         { return prefs.getInt(KEY_USER_ID, -1); }
    public String getUsername()    { return prefs.getString(KEY_USERNAME, ""); }

    public void logout() {
        prefs.edit().clear().apply();
    }
}

