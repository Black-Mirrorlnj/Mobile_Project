package com.example.gmodscore.network.model.partida;

import com.google.gson.annotations.SerializedName;

public class PartidaAtiva {
    @SerializedName("id")              public int id;
    @SerializedName("server_id")       public int serverId;
    @SerializedName("mapa")            public String mapa;
    @SerializedName("modo")            public String modo;
    @SerializedName("start_timestamp") public String startTimestamp;
    @SerializedName("status")          public String status;
}

