package com.example.gmodscore.util.session;

import android.content.Context;

public class SessionManager {

    private final SessionPreferences sessionPrefs;

    public SessionManager(Context context) {
        this.sessionPrefs = new SessionPreferences(context);
    }

    public void salvarSessao(Long id, String nome, String login, String perfil) {
        sessionPrefs.salvar(id, nome, login, perfil);
    }

    public void encerrarSessao() {
        sessionPrefs.limpar();
    }

    public boolean estaLogado()      { return sessionPrefs.estaLogado(); }
    public Long    getUsuarioId()    { return sessionPrefs.getId();      }
    public String  getUsuarioNome()  { return sessionPrefs.getNome();    }
    public String  getUsuarioLogin() { return sessionPrefs.getLogin();   }
    public String  getUsuarioPerfil(){ return sessionPrefs.getPerfil();  }
}
