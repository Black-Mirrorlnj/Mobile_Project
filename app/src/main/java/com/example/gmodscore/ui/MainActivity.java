package com.example.gmodscore.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Busca o NavHostFragment pelo ID correto
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

        // Verifica se encontrou antes de usar
        if (navHostFragment == null) return;

        NavController navController = navHostFragment.getNavController();

        // Liga BottomNav ao NavController
        NavigationUI.setupWithNavController(
                binding.bottomNavView, navController);

        // Esconde BottomNav nas telas de entrada
        navController.addOnDestinationChangedListener((ctrl, dest, args) -> {
            if (dest.getId() == R.id.splashFragment
                    || dest.getId() == R.id.loginFragment
                    || dest.getId() == R.id.visitanteFragment) {
                binding.bottomNavView.setVisibility(View.GONE);
            } else {
                binding.bottomNavView.setVisibility(View.VISIBLE);
            }
        });
    }
}