package com.example.gmodscore.feature.player.ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gmodscore.R;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.VH> {

    // Modelo simples de item do ranking
    public static class RankingItem {
        public int posicao;
        public String nick;
        public String steamId;
        public int pontos;

        public RankingItem(int posicao, String nick, String steamId, int pontos) {
            this.posicao = posicao;
            this.nick    = nick;
            this.steamId = steamId;
            this.pontos  = pontos;
        }
    }

    private final List<RankingItem> items;

    public RankingAdapter(List<RankingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        RankingItem item = items.get(pos);

        // Top 3 recebem medalha, resto recebe número
        switch (item.posicao) {
            case 1: h.txtPosicao.setText("🥇"); break;
            case 2: h.txtPosicao.setText("🥈"); break;
            case 3: h.txtPosicao.setText("🥉"); break;
            default: h.txtPosicao.setText(String.valueOf(item.posicao)); break;
        }

        h.txtNick.setText(item.nick);
        h.txtSteamId.setText(item.steamId);
        h.txtPontos.setText(item.pontos + " pts");
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtPosicao, txtNick, txtSteamId, txtPontos;

        VH(@NonNull View v) {
            super(v);
            txtPosicao = v.findViewById(R.id.txtItemPosicao);
            txtNick    = v.findViewById(R.id.txtItemNick);
            txtSteamId = v.findViewById(R.id.txtItemSteamId);
            txtPontos  = v.findViewById(R.id.txtItemPontos);
        }
    }
}
