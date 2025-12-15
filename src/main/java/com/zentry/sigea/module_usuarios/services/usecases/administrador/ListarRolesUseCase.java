package com.zentry.sigea.module_usuarios.services.usecases.administrador;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IRolRepository;

@Component
public class ListarRolesUseCase {
    private final IRolRepository rolRepository;

    public ListarRolesUseCase(
        IRolRepository rolRepository
    ){
        this.rolRepository = rolRepository;
    }

    public List<RolDomainEntity> execute(){
        return rolRepository.findAll();
    }
}
