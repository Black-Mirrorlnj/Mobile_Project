package com.example.gmodscore.feature.visitante.Fragmentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmodscore.R;
import com.example.gmodscore.feature.visitante.VisitanteModel.VisitanteViewModel;

public class VisitanteListagemFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private VisitanteViewModel viewModel;
    private VisitanteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visitante_listagem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_visitantes);
        progressBar = view.findViewById(R.id.progress_visitantes);

        viewModel = new ViewModelProvider(this).get(VisitanteViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new VisitanteAdapter();
        recyclerView.setAdapter(adapter);

        observarVisitantes();
        carregarVisitantes();
    }

    private void observarVisitantes() {
        viewModel.getVisitantes().observe(getViewLifecycleOwner(), visitantes -> {
            if (visitantes != null) {
                adapter.setListaVisitantes(visitantes);
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getErro().observe(getViewLifecycleOwner(), erro -> {
            if (erro != null) {
                Toast.makeText(requireContext(), erro, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void carregarVisitantes() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel.listarVisitantes();
    }
}
