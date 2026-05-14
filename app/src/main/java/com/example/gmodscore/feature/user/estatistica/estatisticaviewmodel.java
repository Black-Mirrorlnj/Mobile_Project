package com.example.gmodscore.estatistica.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gmodscore.estatistica.model.EstatisticaModel;
import com.example.gmodscore.estatistica.repository.EstatisticaRepository;

public class EstatisticaViewModel extends ViewModel {

    private final EstatisticaRepository repository;

    private final MutableLiveData<EstatisticaModel> estatistica = new MutableLiveData<>();
    private final MutableLiveData<String> erro = new MutableLiveData<>();
    private final MutableLiveData<Boolean> carregando = new MutableLiveData<>();

    public EstatisticaViewModel() {
        this.repository = new EstatisticaRepository();
    }

    public LiveData<EstatisticaModel> getEstatistica() { return estatistica; }
    public LiveData<String> getErro() { return erro; }
    public LiveData<Boolean> getCarregando() { return carregando; }

    public void carregarEstatisticaDoJogador(Long jogadorId) {
        carregando.setValue(true);
        repository.buscarPorJogador(jogadorId, new EstatisticaRepository.Callback1<EstatisticaModel>() {
            @Override
            public void onSuccess(EstatisticaModel data) {
                estatistica.postValue(data);
                carregando.postValue(false);
            }

            @Override
            public void onError(String mensagem) {
                erro.postValue(mensagem);
                carregando.postValue(false);
            }
        });
    }

    public void salvarEstatistica(EstatisticaModel model) {
        carregando.setValue(true);
        repository.atualizar(model.getId(), model, new EstatisticaRepository.Callback1<EstatisticaModel>() {
            @Override
            public void onSuccess(EstatisticaModel data) {
                estatistica.postValue(data);
                carregando.postValue(false);
            }

            @Override
            public void onError(String mensagem) {
                erro.postValue(mensagem);
                carregando.postValue(false);
            }
        });
    }
}
