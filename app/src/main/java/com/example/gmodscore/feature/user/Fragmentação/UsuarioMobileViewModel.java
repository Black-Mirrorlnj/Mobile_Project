package com.example.gmodscore.usuariomobile.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gmodscore.usuario.model.UsuarioModel;
import com.example.gmodscore.usuario.repository.UsuarioRepository;

public class UsuarioMobileViewModel extends ViewModel {

    private final UsuarioRepository repository;

    private final MutableLiveData<UsuarioModel> usuario    = new MutableLiveData<>();
    private final MutableLiveData<String>       erro       = new MutableLiveData<>();
    private final MutableLiveData<Boolean>      carregando = new MutableLiveData<>();

    public UsuarioMobileViewModel() {
        this.repository = new UsuarioRepository();
    }

    public LiveData<UsuarioModel> getUsuario()    { return usuario;    }
    public LiveData<String>       getErro()       { return erro;       }
    public LiveData<Boolean>      getCarregando() { return carregando; }

    public void carregarUsuarioLogado(Long id) {
        carregando.setValue(true);
        repository.buscarPorId(id, new UsuarioRepository.Callback1<UsuarioModel>() {
            @Override
            public void onSuccess(UsuarioModel data) {
                usuario.postValue(data);
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
