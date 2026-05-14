package com.example.gmodscore.feature.visitante.Fragmentacao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmodscore.R;
import com.example.gmodscore.feature.visitante.VisitanteModel.Visitante;

import java.util.ArrayList;
import java.util.List;

public class VisitanteAdapter extends RecyclerView.Adapter<VisitanteAdapter.ViewHolder> {

    private List<Visitante> listaVisitantes = new ArrayList<>();

    public void setListaVisitantes(List<Visitante> lista) {
        this.listaVisitantes = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visitante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Visitante v = listaVisitantes.get(position);
        holder.txtNome.setText("Nome: " + v.nome);
        holder.txtKills.setText("Kills: " + v.kills);
        holder.txtEntrada.setText("Entrada: " + (v.entrada != null ? v.entrada : "--"));
        holder.txtSaida.setText("Saída: " + (v.saida != null ? v.saida : "Online"));
    }

    @Override
    public int getItemCount() {
        return listaVisitantes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtKills, txtEntrada, txtSaida;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txt_visitante_nome);
            txtKills = itemView.findViewById(R.id.txt_visitante_kills);
            txtEntrada = itemView.findViewById(R.id.txt_visitante_entrada);
            txtSaida = itemView.findViewById(R.id.txt_visitante_saida);
        }
    }
}
