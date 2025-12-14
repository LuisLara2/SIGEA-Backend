package com.zentry.sigea.module_usuarios.services.usecases.administrador;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IRolRepository;

@Component
public class EditarRolUseCase {
    private final IRolRepository rolRepository;

    public EditarRolUseCase(
        IRolRepository rolRepository
    ){
        this.rolRepository = rolRepository;
    }

    public String execute(
        String id , 
        String nombreRol , 
        String descripcion
    ){
        RolDomainEntity rolDomainEntity = new RolDomainEntity();

        rolDomainEntity.setNombreRol(nombreRol);
        rolDomainEntity.setDescripcion(descripcion);

        rolRepository.update(id, rolDomainEntity);

        return "Rol actualizado con exito";
    }
}
