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
        // TODO: Substituir por chamada real à API Spring Boot
        // Endpoint sugerido: GET /api/jogadores/{id}/estatisticas
        // Tabelas usadas: estatisticas + jogadores

        int kills      = 32;
        int deaths     = 12;
        int dinheiro   = 3400;
        int nivel      = 4;
        int experiencia = 420;
        int tempoJogado = 1400; // segundos — campo tempo_jogado da tabela estatisticas
        String nick    = "Ana";
        String steamId = "76561198000000002";

        binding.txtStatsNick.setText(nick);
        binding.txtStatsSteamId.setText(steamId);
        binding.txtStatsLevel.setText(String.valueOf(nivel));
        binding.txtStatsKills.setText(String.valueOf(kills));
        binding.txtStatsDeaths.setText(String.valueOf(deaths));
        binding.txtStatsMoney.setText(String.valueOf(dinheiro));

        // K/D Ratio calculado no app (kills / deaths)
        double kd = deaths > 0 ? (double) kills / deaths : kills;
        binding.txtStatsKD.setText(String.format("%.2f", kd));

        // Barra de XP: progresso dentro do nível atual (cada nível = 100 XP)
        int xpNoNivelAtual = experiencia % 100;
        binding.txtStatsXp.setText(experiencia + " XP");
        binding.txtStatsXpProximo.setText(xpNoNivelAtual + " / 100 XP para o próximo nível");
        binding.progressXp.setMax(100);
        binding.progressXp.setProgress(xpNoNivelAtual);

        binding.txtStatsPlaytime.setText(formatPlaytime(tempoJogado));
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

