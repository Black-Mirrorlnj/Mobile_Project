package com.example.gmodscore.feature.player.ranking;

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
import com.example.gmodscore.databinding.FragmentPlayerRankingBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.player.Ranking;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        binding.recyclerRanking.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadRanking();
    }

    private void loadRanking() {
        RetrofitClient.getApi()
                .buscarRanking()
                .enqueue(new Callback<List<Ranking>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Ranking>> call,
                                           @NonNull Response<List<Ranking>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Ranking> lista = response.body();
                            preencherRanking(lista);
                        } else {
                            Toast.makeText(requireContext(),
                                    "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Ranking>> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(requireContext(),
                                "Erro de conexão", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void preencherRanking(List<Ranking> lista) {
        // Converte Ranking → RankingAdapter.RankingItem
        List<RankingAdapter.RankingItem> items = new ArrayList<>();
        for (Ranking r : lista) {
            items.add(new RankingAdapter.RankingItem(
                    r.posicao,
                    r.nome    != null ? r.nome    : "Jogador #" + r.jogadorId,
                    r.steamId != null ? r.steamId : "",
                    r.pontos
            ));
        }

        binding.recyclerRanking.setAdapter(new RankingAdapter(items));

        // Destaca posição do jogador logado (TODO: usar ID real do login)
        int meuId = 1;
        for (Ranking r : lista) {
            if (r.jogadorId == meuId) {
                binding.txtMinhaPosicao.setText(
                        "Você está em #" + r.posicao + " com " + r.pontos + " pts");
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
