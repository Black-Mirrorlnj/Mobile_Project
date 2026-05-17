package com.example.gmodscore.network.model.servidor;

import com.google.gson.annotations.SerializedName;

public class Servidor {
    @SerializedName("id")         public int id;
    @SerializedName("nome")       public String nome;
    @SerializedName("server_key") public String serverKey;
    @SerializedName("ativo")      public boolean ativo;
}
