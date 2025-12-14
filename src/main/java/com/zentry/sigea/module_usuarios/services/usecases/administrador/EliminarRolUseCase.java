package com.zentry.sigea.module_usuarios.services.usecases.administrador;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.repositories.IRolRepository;

@Component
public class EliminarRolUseCase {
    private final IRolRepository rolRepository;

    public EliminarRolUseCase(
        IRolRepository rolRepository
    ){
        this.rolRepository = rolRepository;
    }

    public String execute(String id){
        rolRepository.deleteById(id);

        return "Rol eliminado con exito";
    }
}
