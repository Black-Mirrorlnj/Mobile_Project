package com.example.gmodscore.feature.player.score;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentPlayerScoreBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.player.Pontuacao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerScoreFragment extends Fragment {

    private FragmentPlayerScoreBinding binding;

    public PlayerScoreFragment() {
        super(R.layout.fragment_player_score);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerScore.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadPontuacoes();
    }

    private void loadPontuacoes() {
        RetrofitClient.getApi()
                .listarPontuacoes()
                .enqueue(new Callback<List<Pontuacao>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Pontuacao>> call,
                                           @NonNull Response<List<Pontuacao>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            preencherScore(response.body());
                        } else {
                            Toast.makeText(requireContext(),
                                    "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Pontuacao>> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(requireContext(),
                                "Erro de conexão", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void preencherScore(List<Pontuacao> lista) {
        // Resumo
        int total = lista.size();
        int melhor = 0;
        for (Pontuacao p : lista) {
            if (p.scoreFinal > melhor) melhor = p.scoreFinal;
        }
        binding.txtTotalPartidas.setText(String.valueOf(total));
        binding.txtMelhorScore.setText(String.valueOf(melhor));

        // Converte Pontuacao → ScoreAdapter.ScoreItem
        List<ScoreAdapter.ScoreItem> items = new ArrayList<>();
        for (Pontuacao p : lista) {
            items.add(new ScoreAdapter.ScoreItem(
                    p.partidaId,
                    "Partida " + p.partidaId, // TODO: buscar nome do mapa
                    p.scoreInicial,
                    p.scoreFinal,
                    "—",  // TODO: buscar data da partida
                    "—"   // TODO: buscar duração
            ));
        }
        binding.recyclerScore.setAdapter(new ScoreAdapter(items));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

