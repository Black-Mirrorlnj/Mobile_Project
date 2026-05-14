package com.example.gmodscore.feature.visitante.Ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmodscore.R;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<VisitanteRanking> lista = new ArrayList<>();

    public void setLista(List<VisitanteRanking> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VisitanteRanking item = lista.get(position);
        holder.posicao.setText("#" + item.getPosicao());
        holder.nome.setText(item.getNome());
        holder.kills.setText(item.getKills() + " kills");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView posicao, nome, kills;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            posicao = itemView.findViewById(R.id.txt_posicao);
            nome = itemView.findViewById(R.id.txt_nome_ranking);
            kills = itemView.findViewById(R.id.txt_kills_ranking);
        }
    }
}
