package com.example.gmodscore.feature.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gmodscore.R;
import com.example.gmodscore.databinding.FragmentLoginBinding;
import com.example.gmodscore.network.RetrofitClient;
import com.example.gmodscore.network.model.usuario.SessionManager;
import com.example.gmodscore.network.model.usuario.User;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private SessionManager session;
    private boolean modoLogin = true;

    public LoginFragment() { super(R.layout.fragment_login); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(requireContext());

        // Se já estiver logado vai direto para o player
        if (session.estaLogado()) {
            irParaPlayer();
            return;
        }

        configurarAbas();
        configurarBotoes();
    }

    private void configurarAbas() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                modoLogin = tab.getPosition() == 0;
                binding.btnAcao.setText(modoLogin ? "Entrar" : "Cadastrar");
                binding.layoutConfirmPassword.setVisibility(
                        modoLogin ? View.GONE : View.VISIBLE);
                esconderErro();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void configurarBotoes() {

        binding.btnAcao.setOnClickListener(v -> {
            String user = binding.inputUsername.getText().toString().trim();
            String pass = binding.inputPassword.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                mostrarErro("Preencha todos os campos");
                return;
            }

            if (!modoLogin) {
                String confirm = binding.inputConfirmPassword
                        .getText().toString().trim();
                if (!pass.equals(confirm)) {
                    mostrarErro("As senhas não coincidem");
                    return;
                }
                cadastrar(user, pass);
            } else {
                login(user, pass);
            }
        });

        // Entrar como visitante
        binding.btnVisitante.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_login_to_visitante));
    }

    private void login(String username, String password) {
        showLoading(true);
        // TODO: trocar por POST /login com JWT quando o backend implementar
        RetrofitClient.getApi().listarUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call,
                                   @NonNull Response<List<User>> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    for (User u : response.body()) {
                        if (u.username.equals(username)
                                && u.password.equals(password)) {
                            session.salvarSessao(u.userId, u.username);
                            irParaPlayer();
                            return;
                        }
                    }
                    mostrarErro("Usuário ou senha incorretos");
                } else {
                    mostrarErro("Erro ao conectar ao servidor");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call,
                                  @NonNull Throwable t) {
                showLoading(false);
                mostrarErro("Sem conexão com o servidor");
            }
        });
    }

    private void cadastrar(String username, String password) {
        showLoading(true);
        User novoUser = new User();
        novoUser.username = username;
        novoUser.password = password;

        RetrofitClient.getApi().criarUser(novoUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call,
                                   @NonNull Response<User> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    User criado = response.body();
                    session.salvarSessao(criado.userId, criado.username);
                    irParaPlayer();
                } else {
                    mostrarErro("Usuário já existe ou erro no servidor");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                showLoading(false);
                mostrarErro("Sem conexão com o servidor");
            }
        });
    }

    private void irParaPlayer() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_login_to_player);
    }

    private void mostrarErro(String msg) {
        binding.txtErro.setText(msg);
        binding.txtErro.setVisibility(View.VISIBLE);
    }

    private void esconderErro() {
        binding.txtErro.setVisibility(View.GONE);
    }

    private void showLoading(boolean show) {
        binding.progressLogin.setVisibility(show ? View.VISIBLE : View.GONE);
        binding.btnAcao.setEnabled(!show);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}