package com.example.gmodscore.network.model.usuario;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("login") public String username;
    @SerializedName("senha") public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

