package com.example.gmodscore.feature.player.playerstats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.gmodscore.R;

import com.example.gmodscore.databinding.FragmentPlayerStatsBinding;

public class PlayerStatsFragment extends Fragment {

    private FragmentPlayerStatsBinding binding;

    public PlayerStatsFragment() {
        super(R.layout.fragment_player_stats);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayerStatsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadPlayerStats();
    }

    private void loadPlayerStats() {
        int jogadorId = 1; // TODO: pegar do login

        RetrofitClient.getApi()
                .buscarEstatisticasPorJogador(jogadorId)
                .enqueue(new Callback<Estatisticas>() {

                    @Override
                    public void onResponse(Call<Estatisticas> call,
                                           Response<Estatisticas> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Estatisticas e = response.body();
                            binding.txtStatsKills.setText(String.valueOf(e.kills));
                            binding.txtStatsDeaths.setText(String.valueOf(e.deaths));
                            binding.txtStatsMoney.setText(String.valueOf(e.dinheiro));
                            binding.txtStatsLevel.setText(String.valueOf(e.nivel));
                            binding.txtStatsXp.setText(e.experiencia + " XP");
                            double kd = e.deaths > 0 ? (double) e.kills / e.deaths : e.kills;
                            binding.txtStatsKD.setText(String.format("%.2f", kd));
                            binding.txtStatsPlaytime.setText(formatPlaytime(e.tempoJogado));
                        }
                    }

                    @Override
                    public void onFailure(Call<Estatisticas> call, Throwable t) {
                        Toast.makeText(requireContext(),
                                "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }

    // Converte segundos para formato legível (ex: 23h 20m)
    private String formatPlaytime(int segundos) {
        int h = segundos / 3600;
        int m = (segundos % 3600) / 60;
        if (h > 0) return h + "h " + m + "m";
        return m + "m";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

