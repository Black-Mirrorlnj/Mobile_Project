package com.example.gmodscore.feature.player.score;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gmodscore.R;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.VH> {

    // Modelo baseado em pontuacoes JOIN partidas do banco
    public static class ScoreItem {
        public int    partidaId;
        public String mapa;
        public int    scoreInicial;
        public int    scoreFinal;
        public String dataInicio;   // campo data_inicio da tabela partidas
        public String duracao;      // calculado: data_fim - data_inicio

        public ScoreItem(int partidaId, String mapa, int scoreInicial,
                         int scoreFinal, String dataInicio, String duracao) {
            this.partidaId    = partidaId;
            this.mapa         = mapa;
            this.scoreInicial = scoreInicial;
            this.scoreFinal   = scoreFinal;
            this.dataInicio   = dataInicio;
            this.duracao      = duracao;
        }
    }

    private final List<ScoreItem> items;

    public ScoreAdapter(List<ScoreItem> items) { this.items = items; }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        ScoreItem item = items.get(pos);

        h.txtMapa.setText(item.mapa);
        h.txtScoreFinal.setText(item.scoreFinal + " pts");
        h.txtPartidaId.setText("Partida #" + item.partidaId);
        h.txtData.setText(item.dataInicio);
        h.txtScoreInicial.setText(String.valueOf(item.scoreInicial));
        h.txtScoreFinalDetalhe.setText(String.valueOf(item.scoreFinal));
        h.txtDuracao.setText(item.duracao);
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtMapa, txtScoreFinal, txtPartidaId, txtData,
                txtScoreInicial, txtScoreFinalDetalhe, txtDuracao;

        VH(@NonNull View v) {
            super(v);
            txtMapa             = v.findViewById(R.id.txtItemMapa);
            txtScoreFinal       = v.findViewById(R.id.txtItemScoreFinal);
            txtPartidaId        = v.findViewById(R.id.txtItemPartidaId);
            txtData             = v.findViewById(R.id.txtItemData);
            txtScoreInicial     = v.findViewById(R.id.txtItemScoreInicial);
            txtScoreFinalDetalhe= v.findViewById(R.id.txtItemScoreFinalDetalhe);
            txtDuracao          = v.findViewById(R.id.txtItemDuracao);
        }
    }
}
