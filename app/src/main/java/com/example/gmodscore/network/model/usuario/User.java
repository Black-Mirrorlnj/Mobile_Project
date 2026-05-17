package com.example.gmodscore.network.model.usuario;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")    public int userId;
    @SerializedName("username")  public String username;
    @SerializedName("password")  public String password;
    @SerializedName("createdAt") public String createdAt;
}
