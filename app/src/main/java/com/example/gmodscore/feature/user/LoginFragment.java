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
import com.example.gmodscore.network.model.usuario.LoginRequest;
import com.example.gmodscore.network.model.usuario.User;
import com.google.android.material.tabs.TabLayout;

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

                // Campos extras só aparecem no cadastro
                int vis = modoLogin ? View.GONE : View.VISIBLE;
                binding.layoutConfirmPassword.setVisibility(vis);
                binding.layoutNome.setVisibility(vis);
                binding.layoutEmail.setVisibility(vis);
                esconderErro();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void configurarBotoes() {
        binding.btnAcao.setOnClickListener(v -> {
            String loginStr = binding.inputUsername.getText().toString().trim();
            String senha    = binding.inputPassword.getText().toString().trim();

            if (loginStr.isEmpty() || senha.isEmpty()) {
                mostrarErro("Preencha todos os campos");
                return;
            }

            if (modoLogin) {
                login(loginStr, senha);
            } else {
                String confirm = binding.inputConfirmPassword.getText().toString().trim();
                String nome    = binding.inputNome.getText().toString().trim();
                String email   = binding.inputEmail.getText().toString().trim();

                if (!senha.equals(confirm)) {
                    mostrarErro("As senhas não coincidem");
                    return;
                }
                if (nome.isEmpty() || email.isEmpty()) {
                    mostrarErro("Preencha nome e email");
                    return;
                }
                cadastrar(nome, email, loginStr, senha);
            }
        });

        binding.btnVisitante.setOnClickListener(v ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_login_to_visitante));
    }

    private void login(String login, String senha) {
        showLoading(true);
        LoginRequest request = new LoginRequest(login, senha);

        RetrofitClient.getApi().login(request).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call,
                                   @NonNull Response<User> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    User u = response.body();
                    session.salvarSessao((int) u.id, u.login, u.nome);
                    irParaPlayer();
                } else {
                    mostrarErro("Login ou senha incorretos");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                showLoading(false);
                mostrarErro("Sem conexão com o servidor");
            }
        });
    }

    private void cadastrar(String nome, String email,
                           String login, String senha) {
        showLoading(true);

        User novoUser   = new User();
        novoUser.nome   = nome;
        novoUser.email  = email;
        novoUser.login  = login;
        novoUser.senha  = senha;
        novoUser.perfil = "JOGADOR"; // perfil padrão
        novoUser.ativo  = true;

        RetrofitClient.getApi().criarUsuario(novoUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call,
                                   @NonNull Response<User> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    User u = response.body();
                    session.salvarSessao((int) u.id, u.login, u.nome);
                    irParaPlayer();
                } else {
                    mostrarErro("Erro ao cadastrar. Tente outro login.");
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