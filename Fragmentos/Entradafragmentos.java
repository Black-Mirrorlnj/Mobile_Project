package com.example.gmodscore;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Fragment de Entrada do Visitante — RF001 (visual)
 * Registra o nome do visitante antes de entrar no servidor.
 */
public class EntradaFragment extends Fragment {

    private TextInputLayout   tilNome;
    private TextInputEditText etNome;
    private TextView          tvErro;
    private TextView          tvNomeRegistrado;
    private MaterialButton    btnRegistrar;
    private MaterialButton    btnNovaEntrada;
    private View              cardSucesso;

    // ─── Infla o layout do fragment ───────────────────────────────
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entrada, container, false);
    }

    // ─── Inicializa views e listeners após a view ser criada ──────
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializarViews(view);
        configurarListeners(view);
    }

    // ─── Inicializa as views ──────────────────────────────────────
    private void inicializarViews(View view) {
        tilNome          = view.findViewById(R.id.tilNome);
        etNome           = view.findViewById(R.id.etNome);
        tvErro           = view.findViewById(R.id.tvErro);
        tvNomeRegistrado = view.findViewById(R.id.tvNomeRegistrado);
        btnRegistrar     = view.findViewById(R.id.btnRegistrar);
        btnNovaEntrada   = view.findViewById(R.id.btnNovaEntrada);
        cardSucesso      = view.findViewById(R.id.cardSucesso);
    }

    // ─── Configura listeners ──────────────────────────────────────
    private void configurarListeners(View view) {

        // Esconde erro enquanto digita
        etNome.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                esconderErro();
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Botão registrar
        btnRegistrar.setOnClickListener(v -> registrarEntrada());

        // Botão nova entrada
        btnNovaEntrada.setOnClickListener(v -> resetarFormulario());

        // Botão ver listagem — navega para ListagemFragment
        MaterialButton btnVerListagem = view.findViewById(R.id.btnVerListagem);
        if (btnVerListagem != null) {
            btnVerListagem.setOnClickListener(v ->
                Navigation.findNavController(v)
                    .navigate(R.id.action_entradaFragment_to_listagemFragment)
            );
        }

        // Botão ver ranking — navega para RankingFragment
        MaterialButton btnVerRanking = view.findViewById(R.id.btnVerRanking);
        if (btnVerRanking != null) {
            btnVerRanking.setOnClickListener(v ->
                Navigation.findNavController(v)
                    .navigate(R.id.action_entradaFragment_to_rankingFragment)
            );
        }
    }

    // ─── Valida e registra a entrada ──────────────────────────────
    private void registrarEntrada() {
        String nome = etNome.getText() != null
                ? etNome.getText().toString().trim() : "";

        if (nome.isEmpty()) {
            mostrarErro(getString(R.string.erro_nome_vazio));
            return;
        }
        if (nome.length() < 3) {
            mostrarErro(getString(R.string.erro_nome_curto));
            return;
        }
        if (!nome.matches("^[a-zA-Z0-9_ ]+$")) {
            mostrarErro(getString(R.string.erro_nome_invalido));
            return;
        }

        esconderTeclado();

        // ✅ Futuramente: chamada HTTP para POST /visitantes/entrada
        mostrarSucesso(nome);
    }

    private void mostrarSucesso(String nome) {
        esconderErro();
        tvNomeRegistrado.setText("Bem-vindo, " + nome + "!");
        cardSucesso.setVisibility(View.VISIBLE);
        btnRegistrar.setEnabled(false);
        etNome.setEnabled(false);
    }

    private void resetarFormulario() {
        etNome.setText("");
        etNome.setEnabled(true);
        btnRegistrar.setEnabled(true);
        cardSucesso.setVisibility(View.GONE);
        esconderErro();
        etNome.requestFocus();
    }

    private void mostrarErro(String mensagem) {
        tvErro.setText(mensagem);
        tvErro.setVisibility(View.VISIBLE);
    }

    private void esconderErro() {
        tvErro.setVisibility(View.GONE);
        tvErro.setText("");
    }

    private void esconderTeclado() {
        if (getActivity() == null) return;
        View view = getActivity().getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
