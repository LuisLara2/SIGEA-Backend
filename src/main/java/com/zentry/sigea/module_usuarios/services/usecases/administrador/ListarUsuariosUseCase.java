package com.zentry.sigea.module_usuarios.services.usecases.administrador;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;

@Component
public class ListarUsuariosUseCase {
    private final IUsuarioRepository usuarioRepository;

    public ListarUsuariosUseCase(
        IUsuarioRepository usuarioRepository
    ){
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDomainEntity> execute(){
        return usuarioRepository.findAll();
    }
}
