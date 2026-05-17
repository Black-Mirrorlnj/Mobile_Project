package com.example.gmodscore.feature.play;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.gmodscore.databinding.FragmentPlayBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.usuario.SessionManager;
import com.example.gmodscore.network.model.player.Estatisticas;
import com.example.gmodscore.network.model.partida.PartidaAtiva;
import com.example.gmodscore.network.model.player.Ranking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayFragment extends Fragment {

    // TODO: substituir pelo IP/porta real do seu servidor GMOD
    private static final String SERVER_IP   = "SEU_IP_AQUI";
    private static final String SERVER_PORT = "27015";
    private static final String SERVER_NOME = "GmodScore BR #1";

    private FragmentPlayBinding binding;
    private SessionManager session;

    public PlayFragment() { super(R.layout.fragment_play); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPlayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(requireContext());

        configurarInfoServidor();
        carregarRankDoJogador();
        carregarPartidasAtivas();
        configurarBotoes();
    }

    // ── Preenche info estática do servidor ────────────────
    private void configurarInfoServidor() {
        binding.txtServidorNome.setText(SERVER_NOME);
        binding.txtServerIp.setText(SERVER_IP + ":" + SERVER_PORT);
        binding.txtServidorStatus.setText("● ONLINE");
        binding.txtServidorStatus.setTextColor(0xFF1DB954);
        // TODO: fazer ping real ao servidor para verificar status
    }

    // ── Carrega rank e stats rápidos do jogador logado ────
    private void carregarRankDoJogador() {
        int userId = session.getUserId();
        if (userId == -1) return;

        // Busca posição no ranking
        RetrofitClient.getApi().buscarRanking()
                .enqueue(new Callback<List<Ranking>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Ranking>> call,
                                           @NonNull Response<List<Ranking>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            for (Ranking r : response.body()) {
                                if (r.jogadorId == userId) {
                                    binding.txtPlayRankPosicao.setText("#" + r.posicao + " Global");
                                    binding.txtPlayPontos.setText(String.valueOf(r.pontos));
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Ranking>> call,
                                          @NonNull Throwable t) {}
                });

        // Busca K/D do jogador
        RetrofitClient.getApi().buscarEstatisticasPorJogador(userId)
                .enqueue(new Callback<Estatisticas>() {
                    @Override
                    public void onResponse(@NonNull Call<Estatisticas> call,
                                           @NonNull Response<Estatisticas> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Estatisticas e = response.body();
                            double kd = e.deaths > 0
                                    ? (double) e.kills / e.deaths : e.kills;
                            binding.txtPlayKD.setText(String.format("%.2f", kd));

                            // Atualiza info ao vivo do mapa (mock — substituir pela API do servidor)
                            binding.txtMapaAtual.setText("gm_construct");
                            binding.txtModoAtual.setText("TDM");
                            binding.txtJogadoresOnline.setText("5 / 16");
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Estatisticas> call,
                                          @NonNull Throwable t) {}
                });
    }

    // ── Carrega lista de partidas ativas ──────────────────
    private void carregarPartidasAtivas() {
        binding.recyclerPartidasAtivas.setLayoutManager(
                new LinearLayoutManager(requireContext()));

        RetrofitClient.getApi().listarMatches()
                .enqueue(new Callback<List<PartidaAtiva>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<PartidaAtiva>> call,
                                           @NonNull Response<List<PartidaAtiva>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<PartidaAtiva> lista = response.body();

                            if (lista.isEmpty()) {
                                // Nenhuma partida ativa no momento
                                binding.recyclerPartidasAtivas.setVisibility(View.GONE);
                            } else {
                                binding.recyclerPartidasAtivas.setAdapter(
                                        new PartidaAtivaAdapter(lista, partida -> conectarAoServidor())
                                );
                            }
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<PartidaAtiva>> call,
                                          @NonNull Throwable t) {
                        binding.recyclerPartidasAtivas.setVisibility(View.GONE);
                    }
                });
    }

    // ── Configura botões ──────────────────────────────────
    private void configurarBotoes() {

        // Botão JOGAR AGORA — abre o GMOD via Steam
        binding.btnJogar.setOnClickListener(v -> conectarAoServidor());

        // Copiar IP
        binding.btnCopiarIp.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("IP",
                    SERVER_IP + ":" + SERVER_PORT);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(requireContext(),
                    "IP copiado!", Toast.LENGTH_SHORT).show();
        });
    }

    // ── Abre o GMOD via link Steam ─────────────────────────
    private void conectarAoServidor() {
        String steamUrl = "steam://connect/" + SERVER_IP + ":" + SERVER_PORT;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(steamUrl));
            startActivity(intent);
        } catch (Exception e) {
            // Steam não instalado — mostra IP para conectar manualmente
            Toast.makeText(requireContext(),
                    "Abra o GMOD e conecte em: " + SERVER_IP + ":" + SERVER_PORT,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

