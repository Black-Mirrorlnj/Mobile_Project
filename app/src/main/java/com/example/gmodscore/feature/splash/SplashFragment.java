package com.example.gmodscore.feature.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentSplashBinding;
import com.example.gmodscore.network.model.usuario.SessionManager;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;
    private SessionManager session;

    public SplashFragment() { super(R.layout.fragment_splash); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(requireContext());
        iniciarAnimacoes();
    }

    private void iniciarAnimacoes() {
        // 1) Logo aparece com scale + fade — 600ms
        binding.imgSplashLogo.animate()
                .alpha(1f).scaleX(1f).scaleY(1f)
                .setDuration(600)
                .withEndAction(() -> {

                    // 2) Título aparece — 400ms
                    binding.txtSplashTitulo.animate()
                            .alpha(1f).translationYBy(-10f)
                            .setDuration(400)
                            .start();

                    // 3) Subtítulo aparece — 400ms com delay
                    binding.txtSplashSubtitulo.animate()
                            .alpha(1f)
                            .setStartDelay(200)
                            .setDuration(400)
                            .start();

                    // 4) Barra de loading aparece — 400ms com delay
                    binding.progressSplash.animate()
                            .alpha(1f)
                            .setStartDelay(400)
                            .setDuration(400)
                            .start();

                    // 5) Status aparece — 400ms com delay
                    binding.txtSplashStatus.animate()
                            .alpha(1f)
                            .setStartDelay(500)
                            .setDuration(400)
                            .withEndAction(this::verificarSessaoENavegar)
                            .start();
                })
                .start();
    }

    private void verificarSessaoENavegar() {
        // Aguarda 1.5s mostrando o loading, depois decide para onde ir
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            if (binding == null) return;

            if (session.estaLogado()) {
                // Usuário já logado → vai direto para o Player
                binding.txtSplashStatus.setText("Bem-vindo, " + session.getNome() + "!");
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding == null) return;
                    NavHostFragment.findNavController(this)
                            .navigate(R.id.action_splash_to_player);
                }, 800);
            } else {
                // Não logado → vai para Login
                binding.txtSplashStatus.setText("Pronto!");
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding == null) return;
                    NavHostFragment.findNavController(this)
                            .navigate(R.id.action_splash_to_login);
                }, 600);
            }

        }, 1500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
