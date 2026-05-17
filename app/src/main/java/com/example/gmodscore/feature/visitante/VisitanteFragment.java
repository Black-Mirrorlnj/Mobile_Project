package com.example.gmodscore.feature.visitante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentVisitanteBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.visitante.Visitante;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitanteFragment extends Fragment {

    private FragmentVisitanteBinding binding;

    public VisitanteFragment() {
        super(R.layout.fragment_visitante);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitanteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerVisitantes.setLayoutManager(
                new LinearLayoutManager(requireContext()));

        carregarRankingVisitantes();
        configurarBotoes();
    }

    private void configurarBotoes() {

        // Buscar visitante pelo nome
        binding.btnBuscarVisitante.setOnClickListener(v -> {
            String nome = binding.inputNomeVisitante.getText().toString().trim();
            if (nome.isEmpty()) {
                mostrarErro("Digite um nome para buscar");
                return;
            }
            buscarVisitante(nome);
        });

        // Registrar nova entrada
        binding.btnRegistrarEntrada.setOnClickListener(v -> {
            String nome = binding.inputNomeVisitante.getText().toString().trim();
            if (nome.isEmpty()) {
                mostrarErro("Digite seu nome antes de registrar");
                return;
            }
            registrarEntrada(nome);
        });

        // Voltar para login
        binding.btnVoltarLogin.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_visitante_to_login));
    }

    private void buscarVisitante(String nome) {
        RetrofitClient.getApi().listarVisitantes()
                .enqueue(new Callback<List<Visitante>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Visitante>> call,
                                           @NonNull Response<List<Visitante>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            for (Visitante v : response.body()) {
                                if (v.nomeUsuario.equalsIgnoreCase(nome)) {
                                    exibirCardVisitante(v);
                                    return;
                                }
                            }
                            mostrarErro("Visitante não encontrado");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Visitante>> call,
                                          @NonNull Throwable t) {
                        mostrarErro("Erro de conexão");
                    }
                });
    }

    private void registrarEntrada(String nome) {
        Visitante novo = new Visitante();
        novo.nomeUsuario = nome;

        RetrofitClient.getApi().registrarEntrada(novo)
                .enqueue(new Callback<Visitante>() {
                    @Override
                    public void onResponse(@NonNull Call<Visitante> call,
                                           @NonNull Response<Visitante> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            exibirCardVisitante(response.body());
                            carregarRankingVisitantes(); // atualiza lista
                        } else {
                            mostrarErro("Erro ao registrar entrada");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Visitante> call,
                                          @NonNull Throwable t) {
                        mostrarErro("Erro de conexão");
                    }
                });
    }

    private void carregarRankingVisitantes() {
        RetrofitClient.getApi().listarVisitantes()
                .enqueue(new Callback<List<Visitante>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Visitante>> call,
                                           @NonNull Response<List<Visitante>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Visitante> lista = response.body();
                            // Ordena por kills decrescente
                            lista.sort((a, b) -> b.kills - a.kills);
                            binding.recyclerVisitantes
                                    .setAdapter(new VisitanteAdapter(lista));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Visitante>> call,
                                          @NonNull Throwable t) {
                    }
                });
    }

    private void exibirCardVisitante(Visitante v) {
        binding.cardStatsVisitante.setVisibility(View.VISIBLE);
        binding.txtVisitanteNome.setText(v.nomeUsuario);
        binding.txtVisitanteKills.setText(String.valueOf(v.kills));
        String horario = v.horarioEntrada != null
                ? "Entrou: " + v.horarioEntrada.substring(11, 16) : "";
        binding.txtVisitanteHorario.setText(horario);
        binding.txtErroVisitante.setVisibility(View.GONE);
    }

    private void mostrarErro(String msg) {
        binding.txtErroVisitante.setText(msg);
        binding.txtErroVisitante.setVisibility(View.VISIBLE);
        binding.cardStatsVisitante.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

