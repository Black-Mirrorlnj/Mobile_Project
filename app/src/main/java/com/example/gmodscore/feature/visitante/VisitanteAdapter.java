package com.example.gmodscore.feature.visitante;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmodscore.R;
import com.example.gmodscore.network.model.visitante.Visitante;

import java.util.List;

public class VisitanteAdapter extends RecyclerView.Adapter<VisitanteAdapter.VH> {

    private final List<Visitante> items;

    public VisitanteAdapter(List<Visitante> items) { this.items = items; }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visitante, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Visitante v = items.get(pos);
        int posicao  = pos + 1;

        switch (posicao) {
            case 1: h.posicao.setText("🥇"); break;
            case 2: h.posicao.setText("🥈"); break;
            case 3: h.posicao.setText("🥉"); break;
            default: h.posicao.setText(String.valueOf(posicao)); break;
        }

        h.nome.setText(v.nomeUsuario);
        h.kills.setText(v.kills + " kills");
        h.horario.setText(v.horarioEntrada != null
                ? "Entrou: " + v.horarioEntrada.substring(11, 16) : "");
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView posicao, nome, kills, horario;
        VH(@NonNull View v) {
            super(v);
            posicao = v.findViewById(R.id.txtVisitantePosicao);
            nome    = v.findViewById(R.id.txtVisitanteItemNome);
            kills   = v.findViewById(R.id.txtVisitanteItemKills);
            horario = v.findViewById(R.id.txtVisitanteItemHorario);
        }
    }
}
