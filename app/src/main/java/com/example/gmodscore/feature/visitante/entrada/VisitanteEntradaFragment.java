package com.example.gmodscore.feature.visitante.entrada;

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

import com.example.gmodscore.R;
import com.example.gmodscore.network.VisitanteApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisitanteEntradaFragment extends Fragment {

    private EditText inputNome;
    private Button btnRegistrar;
    private VisitanteApiService api;

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

        inputNome    = view.findViewById(R.id.input_nome_visitante);
        btnRegistrar = view.findViewById(R.id.btn_registrar_entrada);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend-production-db6e.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(VisitanteApiService.class);

        btnRegistrar.setOnClickListener(v -> registrarEntrada());
    }

    private void registrarEntrada() {
        String nome = inputNome.getText().toString().trim();
        if (nome.isEmpty()) {
            Toast.makeText(requireContext(), "Digite o nome do visitante", Toast.LENGTH_SHORT).show();
            return;
        }

        api.registrarEntrada(nome).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(requireContext(), "Entrada registrada!", Toast.LENGTH_SHORT).show();
                    inputNome.setText("");
                } else {
                    Toast.makeText(requireContext(), "Erro: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Falha de conexao", Toast.LENGTH_SHORT).show();
            }
        });
    }
}