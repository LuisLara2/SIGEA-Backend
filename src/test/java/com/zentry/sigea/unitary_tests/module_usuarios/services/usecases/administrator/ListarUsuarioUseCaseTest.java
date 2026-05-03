package com.zentry.sigea.unitary_tests.module_usuarios.services.usecases.administrator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.services.serviceDTO.ListarUsuariosServiceDTO;
import com.zentry.sigea.module_usuarios.services.usecases.administrador.ListarUsuariosUseCase;

@ExtendWith(MockitoExtension.class)
public class ListarUsuarioUseCaseTest {
    
    @Mock
    private IUsuarioRepository usuarioRepository; 

    @Mock
    private IUsuarioRolRepository usuarioRolRepository;

    @InjectMocks 
    private ListarUsuariosUseCase listarUsuariosUseCase;

    @Test
    public void retornarListadoDeUsuarios(){
        List<UsuarioDomainEntity> listUsuarioDomainEntities = List.of(
            UsuarioDomainEntity.create("Pepe", "Rondero", "wasd@gmail.com", "1234", "94565645", false, "967567456", "+51"),
            UsuarioDomainEntity.create("Ana", "Liza Melano", "wasd@gmail.com", "1234", "945656500", false, "967567400", "+51")
        );

        listUsuarioDomainEntities.get(0).setId("USER_1_ID");
        listUsuarioDomainEntities.get(1).setId("USER_2_ID");

        List<RolDomainEntity> listRolDomainEntities_1 = List.of(
            RolDomainEntity.create("ROL_PARTICIPANTE", "Rol particupante")
        );

        List<RolDomainEntity> listRolDomainEntities_2 = List.of(
            RolDomainEntity.create("ROL_ADMIN", "Rol Admin")
        );

        when(usuarioRolRepository.findRolesByUsuarioId(
            listUsuarioDomainEntities.get(0).getId()
        )).thenReturn(listRolDomainEntities_1);
        when(usuarioRolRepository.findRolesByUsuarioId(
            listUsuarioDomainEntities.get(1).getId()
        )).thenReturn(listRolDomainEntities_2);

        when(usuarioRepository.findAll()).thenReturn(listUsuarioDomainEntities);

        List<ListarUsuariosServiceDTO> resultTest = listarUsuariosUseCase.execute();

        verify(usuarioRolRepository).findRolesByUsuarioId(listUsuarioDomainEntities.get(0).getId());
        verify(usuarioRolRepository).findRolesByUsuarioId(listUsuarioDomainEntities.get(1).getId());

        verify(usuarioRepository).findAll();

        assertEquals(2, resultTest.size());
    }

}
