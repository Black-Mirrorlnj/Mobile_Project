package com.example.gmodscore.usuariomobile.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gmodscore.R;
import com.example.gmodscore.usuario.model.UsuarioModel;
import com.example.gmodscore.util.session.SessionManager;

public class UsuarioMobileFragment extends Fragment {

    private UsuarioMobileViewModel viewModel;
    private SessionManager         sessionManager;

    // Views
    private TextView    tvNome;
    private TextView    tvEmail;
    private TextView    tvLogin;
    private TextView    tvPerfil;
    private TextView    tvCriadoEm;
    private TextView    tvAtivo;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usuario_mobile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews(view);
        setupViewModel();
        carregarDados();
    }

    // --- Setup ---

    private void bindViews(View view) {
        tvNome      = view.findViewById(R.id.tv_usuario_nome);
        tvEmail     = view.findViewById(R.id.tv_usuario_email);
        tvLogin     = view.findViewById(R.id.tv_usuario_login);
        tvPerfil    = view.findViewById(R.id.tv_usuario_perfil);
        tvCriadoEm  = view.findViewById(R.id.tv_usuario_criado_em);
        tvAtivo     = view.findViewById(R.id.tv_usuario_ativo);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void setupViewModel() {
        sessionManager = new SessionManager(requireContext());
        viewModel = new ViewModelProvider(this).get(UsuarioMobileViewModel.class);

        viewModel.getCarregando().observe(getViewLifecycleOwner(), carregando ->
                progressBar.setVisibility(carregando ? View.VISIBLE : View.GONE));

        viewModel.getErro().observe(getViewLifecycleOwner(), mensagem ->
                Toast.makeText(requireContext(), mensagem, Toast.LENGTH_SHORT).show());

        viewModel.getUsuario().observe(getViewLifecycleOwner(), this::preencherTela);
    }

    private void carregarDados() {
        Long id = sessionManager.getUsuarioId();
        if (id != null && id != -1L) {
            viewModel.carregarUsuarioLogado(id);
        } else {
            Toast.makeText(requireContext(), "Sessão inválida", Toast.LENGTH_SHORT).show();
        }
    }

    // --- UI ---

    private void preencherTela(UsuarioModel usuario) {
        tvNome.setText(usuario.getNome());
        tvEmail.setText(usuario.getEmail());
        tvLogin.setText(usuario.getLogin());
        tvPerfil.setText(usuario.getPerfil());
        tvCriadoEm.setText(usuario.getCriadoEm());
        tvAtivo.setText(Boolean.TRUE.equals(usuario.getAtivo()) ? "Ativo" : "Inativo");
    }
}
