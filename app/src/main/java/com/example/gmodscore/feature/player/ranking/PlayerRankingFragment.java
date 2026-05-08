package com.example.gmodscore.feature.player.ranking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gmodscore.databinding.FragmentPlayerRankingBinding;
import com.example.gmodscore.R;

import java.util.Arrays;
import java.util.List;

public class PlayerRankingFragment extends Fragment {

    private FragmentPlayerRankingBinding binding;

    public PlayerRankingFragment() {
        super(R.layout.fragment_player_ranking);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerRankingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRanking();
    }

    private void setupRanking() {
        // TODO: Substituir por chamada real à API Spring Boot
        // Endpoint sugerido: GET /api/ranking
        // Tabelas usadas: ranking_global JOIN jogadores ORDER BY pontos DESC

        List<RankingAdapter.RankingItem> lista = Arrays.asList(
                new RankingAdapter.RankingItem(1, "Julia",  "76561198000000004", 3200),
                new RankingAdapter.RankingItem(2, "Ana",    "76561198000000002", 2100),
                new RankingAdapter.RankingItem(3, "Carlos", "76561198000000001", 1200),
                new RankingAdapter.RankingItem(4, "Marcos", "76561198000000005", 1500),
                new RankingAdapter.RankingItem(5, "Pedro",  "76561198000000003",  900)
        );

        binding.recyclerRanking.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerRanking.setAdapter(new RankingAdapter(lista));

        // Destaca a posição do jogador logado (mock: jogador "Ana" = posição 2)
        binding.txtMinhaPosicao.setText("Você está em #2 com 2100 pts");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

