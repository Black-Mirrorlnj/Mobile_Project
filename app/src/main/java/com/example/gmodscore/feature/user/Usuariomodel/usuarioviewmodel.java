package com.example.gmodscore.usuario.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gmodscore.usuario.model.UsuarioModel;
import com.example.gmodscore.usuario.repository.UsuarioRepository;

import java.util.List;

public class UsuarioViewModel extends ViewModel {

    private final UsuarioRepository repository;

    private final MutableLiveData<UsuarioModel>       usuario    = new MutableLiveData<>();
    private final MutableLiveData<List<UsuarioModel>> usuarios   = new MutableLiveData<>();
    private final MutableLiveData<String>             erro       = new MutableLiveData<>();
    private final MutableLiveData<Boolean>            carregando = new MutableLiveData<>();

    public UsuarioViewModel() {
        this.repository = new UsuarioRepository();
    }

    public LiveData<UsuarioModel>       getUsuario()    { return usuario;    }
    public LiveData<List<UsuarioModel>> getUsuarios()   { return usuarios;   }
    public LiveData<String>             getErro()       { return erro;       }
    public LiveData<Boolean>            getCarregando() { return carregando; }

    public void carregarUsuario(Long id) {
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

    public void carregarTodos() {
        carregando.setValue(true);
        repository.listarTodos(new UsuarioRepository.Callback1<List<UsuarioModel>>() {
            @Override
            public void onSuccess(List<UsuarioModel> data) {
                usuarios.postValue(data);
                carregando.postValue(false);
            }

            @Override
            public void onError(String mensagem) {
                erro.postValue(mensagem);
                carregando.postValue(false);
            }
        });
    }

    public void atualizarUsuario(UsuarioModel model) {
        carregando.setValue(true);
        repository.atualizar(model.getId(), model, new UsuarioRepository.Callback1<UsuarioModel>() {
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
