package com.example.gmodscore.feature.player.score;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gmodscore.databinding.FragmentPlayerScoreBinding;
import com.example.gmodscore.R;

import java.util.Arrays;
import java.util.List;

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
        loadScores();
    }

    private void loadScores() {
        // TODO: Substituir por chamada real à API Spring Boot
        // Endpoint sugerido: GET /api/jogadores/{id}/pontuacoes
        // Tabelas: pontuacoes JOIN partidas WHERE jogador_id = {id}

        List<ScoreAdapter.ScoreItem> lista = Arrays.asList(
                new ScoreAdapter.ScoreItem(1, "gm_construct", 0, 120, "01/04 18:00", "30 min"),
                new ScoreAdapter.ScoreItem(2, "gm_flatgrass",  0,  85, "02/04 19:00", "40 min"),
                new ScoreAdapter.ScoreItem(3, "gm_bigcity",    0, 310, "03/04 20:00", "50 min"),
                new ScoreAdapter.ScoreItem(4, "gm_construct",  0, 150, "04/04 21:00", "35 min"),
                new ScoreAdapter.ScoreItem(5, "gm_flatgrass",  0, 200, "05/04 22:00", "45 min")
        );

        // Calcula resumo
        int total = lista.size();
        int melhor = lista.stream()
                .mapToInt(i -> i.scoreFinal)
                .max()
                .orElse(0);

        binding.txtTotalPartidas.setText(String.valueOf(total));
        binding.txtMelhorScore.setText(String.valueOf(melhor));

        binding.recyclerScore.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerScore.setAdapter(new ScoreAdapter(lista));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
