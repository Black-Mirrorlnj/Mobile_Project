package com.example.gmodscore.feature.visitante.Fragmentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gmodscore.R;
import com.example.gmodscore.feature.visitante.VisitanteModel.VisitanteViewModel;

public class VisitanteSaidaFragment extends Fragment {

    private EditText inputId, inputKills;
    private Button btnRegistrarSaida;
    private VisitanteViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visitante_saida, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputId = view.findViewById(R.id.input_id_visitante);
        inputKills = view.findViewById(R.id.input_kills);
        btnRegistrarSaida = view.findViewById(R.id.btn_registrar_saida);

        viewModel = new ViewModelProvider(this).get(VisitanteViewModel.class);

        btnRegistrarSaida.setOnClickListener(v -> registrarSaida());

        observarResultado();
    }

    private void registrarSaida() {
        String id = inputId.getText().toString().trim();
        String kills = inputKills.getText().toString().trim();

        if (id.isEmpty() || kills.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int visitanteId = Integer.parseInt(id);
            int killsCount = Integer.parseInt(kills);
            viewModel.registrarSaida(visitanteId, killsCount);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Valores inválidos", Toast.LENGTH_SHORT).show();
        }
    }

    private void observarResultado() {
        viewModel.getSucesso().observe(getViewLifecycleOwner(), sucesso -> {
            if (sucesso) {
                Toast.makeText(requireContext(), "Saída registrada com sucesso!", Toast.LENGTH_SHORT).show();
                inputId.setText("");
                inputKills.setText("");
            }
        });

        viewModel.getErro().observe(getViewLifecycleOwner(), erro -> {
            if (erro != null) {
                Toast.makeText(requireContext(), "Erro: " + erro, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
