package com.example.gmodscore.feature.player.playerstats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentPlayerStatsBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.player.Estatisticas;
import com.example.gmodscore.network.model.player.Jogador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        showLoading(true);
        loadJogador(1); // TODO: substituir pelo ID do jogador logado
    }

    // 1) Busca dados do jogador (nick + steamId)
    private void loadJogador(int jogadorId) {
        RetrofitClient.getApi()
                .buscarJogador(jogadorId)
                .enqueue(new Callback<Jogador>() {
                    @Override
                    public void onResponse(@NonNull Call<Jogador> call,
                                           @NonNull Response<Jogador> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Jogador j = response.body();
                            binding.txtStatsNick.setText(j.nome);
                            binding.txtStatsSteamId.setText(j.steamId);
                        }
                        loadEstatisticas(jogadorId); // encadeia a próxima chamada
                    }

                    @Override
                    public void onFailure(@NonNull Call<Jogador> call, @NonNull Throwable t) {
                        loadEstatisticas(jogadorId); // tenta carregar stats mesmo assim
                    }
                });
    }

    // 2) Busca estatísticas do jogador
    private void loadEstatisticas(int jogadorId) {
        RetrofitClient.getApi()
                .buscarEstatisticasPorJogador(jogadorId)
                .enqueue(new Callback<Estatisticas>() {
                    @Override
                    public void onResponse(@NonNull Call<Estatisticas> call,
                                           @NonNull Response<Estatisticas> response) {
                        showLoading(false);
                        if (response.isSuccessful() && response.body() != null) {
                            preencherStats(response.body());
                        } else {
                            Toast.makeText(requireContext(),
                                    "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Estatisticas> call,
                                          @NonNull Throwable t) {
                        showLoading(false);
                        Toast.makeText(requireContext(),
                                "Erro de conexão", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void preencherStats(Estatisticas e) {
        binding.txtStatsKills.setText(String.valueOf(e.kills));
        binding.txtStatsDeaths.setText(String.valueOf(e.deaths));
        binding.txtStatsMoney.setText(String.valueOf(e.dinheiro));
        binding.txtStatsLevel.setText(String.valueOf(e.nivel));

        // K/D Ratio
        double kd = e.deaths > 0 ? (double) e.kills / e.deaths : e.kills;
        binding.txtStatsKD.setText(String.format("%.2f", kd));

        // XP e barra de progresso
        int xpNoNivel = e.experiencia % 100;
        binding.txtStatsXp.setText(e.experiencia + " XP");
        binding.txtStatsXpProximo.setText(xpNoNivel + " / 100 XP para o próximo nível");
        binding.progressXp.setMax(100);
        binding.progressXp.setProgress(xpNoNivel);

        // Tempo jogado
        binding.txtStatsPlaytime.setText(formatPlaytime(e.tempoJogado));
    }

    private String formatPlaytime(int segundos) {
        int h = segundos / 3600;
        int m = (segundos % 3600) / 60;
        return h > 0 ? h + "h " + m + "m" : m + "m";
    }

    // Mostra/esconde loading enquanto carrega da API
    private void showLoading(boolean loading) {
        binding.progressXp.setIndeterminate(loading);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
