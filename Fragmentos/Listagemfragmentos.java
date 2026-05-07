package com.example.gmodscore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment de Listagem dos Visitantes
 * Exibe todos os visitantes registrados no servidor.
 */
public class ListagemFragment extends Fragment {

    private LinearLayout     llListaVisitantes;
    private TextView         tvVazio;
    private MaterialButton   btnVoltar;

    // ─── Dados simulados (futuramente virão da API) ───────────────
    private final List<String[]> visitantesSimulados = new ArrayList<String[]>() {{
        add(new String[]{"1", "PlayerOne",  "15", "OFFLINE"});
        add(new String[]{"2", "SniperXZ",   "32", "ONLINE"});
        add(new String[]{"3", "Noob123",    "3",  "ONLINE"});
        add(new String[]{"4", "DarkMaster", "8",  "OFFLINE"});
    }};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listagem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llListaVisitantes = view.findViewById(R.id.llListaVisitantes);
        tvVazio           = view.findViewById(R.id.tvVazio);
        btnVoltar         = view.findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(v ->
            Navigation.findNavController(v).navigateUp()
        );

        carregarVisitantes();
    }

    // ─── Carrega e exibe os visitantes ────────────────────────────
    private void carregarVisitantes() {
        if (visitantesSimulados.isEmpty()) {
            tvVazio.setVisibility(View.VISIBLE);
            llListaVisitantes.setVisibility(View.GONE);
            return;
        }

        tvVazio.setVisibility(View.GONE);
        llListaVisitantes.setVisibility(View.VISIBLE);
        llListaVisitantes.removeAllViews();

        for (String[] visitante : visitantesSimulados) {
            View itemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_visitante, llListaVisitantes, false);

            TextView tvNome   = itemView.findViewById(R.id.tvItemNome);
            TextView tvKills  = itemView.findViewById(R.id.tvItemKills);
            TextView tvStatus = itemView.findViewById(R.id.tvItemStatus);

            tvNome.setText(visitante[1]);
            tvKills.setText(visitante[2] + " kills");

            boolean online = visitante[3].equals("ONLINE");
            tvStatus.setText(online ? "🟢 Online" : "🔴 Offline");
            tvStatus.setTextColor(getResources().getColor(
                    online ? R.color.text_success : R.color.text_error, null));

            llListaVisitantes.addView(itemView);
        }
    }
}
