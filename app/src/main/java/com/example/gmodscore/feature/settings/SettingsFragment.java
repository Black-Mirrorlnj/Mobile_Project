package com.example.gmodscore.feature.settings;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentSettingsBinding;
import com.example.gmodscore.network.model.usuario.SessionManager;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SessionManager session;

    public SettingsFragment() { super(R.layout.fragment_settings); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(requireContext());

        preencherDadosUsuario();
        configurarBotoes();
    }

    private void preencherDadosUsuario() {
        binding.txtSettingsUsername.setText(session.getUsername());
        binding.txtSettingsNome.setText(session.getNome());
    }

    private void configurarBotoes() {

        // Navegar para Perfil
        binding.itemPerfil.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.playerFragment));

        // Navegar para Stats
        binding.itemStats.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.playerStatsFragment));

        // Deslogar
        binding.btnDeslogar.setOnClickListener(v ->
                new AlertDialog.Builder(requireContext())
                        .setTitle("Deslogar")
                        .setMessage("Tem certeza que deseja sair da conta?")
                        .setPositiveButton("Sair", (dialog, which) -> {
                            session.logout();
                            NavHostFragment.findNavController(this)
                                    .navigate(R.id.action_settings_to_login);
                        })
                        .setNegativeButton("Cancelar", null)
                        .show()
        );

        // Sair do app
        binding.btnSairApp.setOnClickListener(v ->
                new AlertDialog.Builder(requireContext())
                        .setTitle("Sair")
                        .setMessage("Deseja fechar o aplicativo?")
                        .setPositiveButton("Fechar", (dialog, which) ->
                                requireActivity().finishAffinity())
                        .setNegativeButton("Cancelar", null)
                        .show()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

