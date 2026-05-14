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

public class VisitanteEntradaFragment extends Fragment {

    private EditText inputNome;
    private Button btnRegistrar;
    private VisitanteViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visitante_entrada, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputNome = view.findViewById(R.id.input_nome_visitante);
        btnRegistrar = view.findViewById(R.id.btn_registrar_entrada);

        viewModel = new ViewModelProvider(this).get(VisitanteViewModel.class);

        btnRegistrar.setOnClickListener(v -> registrarEntrada());

        observarResultado();
    }

    private void registrarEntrada() {
        String nome = inputNome.getText().toString().trim();
        if (nome.isEmpty()) {
            Toast.makeText(requireContext(), "Digite o nome do visitante", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.registrarEntrada(nome);
    }

    private void observarResultado() {
        viewModel.getSucesso().observe(getViewLifecycleOwner(), sucesso -> {
            if (sucesso) {
                Toast.makeText(requireContext(), "Entrada registrada com sucesso!", Toast.LENGTH_SHORT).show();
                inputNome.setText("");
            }
        });

        viewModel.getErro().observe(getViewLifecycleOwner(), erro -> {
            if (erro != null) {
                Toast.makeText(requireContext(), "Erro: " + erro, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
