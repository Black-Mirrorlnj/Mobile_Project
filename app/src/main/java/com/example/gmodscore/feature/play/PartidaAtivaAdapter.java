package com.example.gmodscore.feature.play;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmodscore.R;
import com.example.gmodscore.network.model.partida.PartidaAtiva;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PartidaAtivaAdapter extends RecyclerView.Adapter<PartidaAtivaAdapter.VH> {

    public interface OnEntrarListener { void onEntrar(PartidaAtiva partida); }

    private final List<PartidaAtiva> items;
    private final OnEntrarListener listener;

    public PartidaAtivaAdapter(List<PartidaAtiva> items, OnEntrarListener listener) {
        this.items    = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_partida_ativa, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        PartidaAtiva p = items.get(pos);
        h.mapa.setText(p.mapa  != null ? p.mapa : "—");
        h.modo.setText(p.modo  != null ? p.modo : "—");
        h.tempo.setText(calcularTempo(p.startTimestamp));
        h.btnEntrar.setOnClickListener(v -> listener.onEntrar(p));
    }

    // Calcula quanto tempo a partida está em andamento
    private String calcularTempo(String startTimestamp) {
        if (startTimestamp == null) return "--:--";
        try {
            // Formato esperado: "2026-04-01T18:00:00"
            long agora = System.currentTimeMillis();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                            java.util.Locale.getDefault());
            long inicio = sdf.parse(startTimestamp).getTime();
            long diff   = (agora - inicio) / 1000 / 60; // em minutos
            return diff + "min";
        } catch (Exception e) {
            return "--:--";
        }
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView mapa, modo, tempo;
        MaterialButton btnEntrar;
        VH(@NonNull View v) {
            super(v);
            mapa      = v.findViewById(R.id.txtPartidaMapa);
            modo      = v.findViewById(R.id.txtPartidaModo);
            tempo     = v.findViewById(R.id.txtPartidaTempo);
            btnEntrar = v.findViewById(R.id.btnEntrarPartida);
        }
    }
}