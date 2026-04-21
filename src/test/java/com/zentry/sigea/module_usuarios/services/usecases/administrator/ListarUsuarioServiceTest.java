package com.zentry.sigea.module_usuarios.services.usecases.administrator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.services.serviceDTO.ListarUsuariosServiceDTO;
import com.zentry.sigea.module_usuarios.services.usecases.administrador.ListarUsuariosUseCase;

@ExtendWith(MockitoExtension.class)
public class ListarUsuarioServiceTest {
    
    @Mock
    private IUsuarioRepository usuarioRepository; 

    @InjectMocks 
    private ListarUsuariosUseCase listarUsuariosUseCase;

    @Test
    void retornarListadoDeUsuarios(){
        List<UsuarioDomainEntity> listUsuarioDomainEntities = List.of(
            UsuarioDomainEntity.create("Pepe", "Rondero", "wasd@gmail.com", "1234", "94565645", false, "967567456", "+51"),
            UsuarioDomainEntity.create("Ana", "Liza Melano", "wasd@gmail.com", "1234", "945656500", false, "967567400", "+51")
        );

        when(usuarioRepository.findAll()).thenReturn(listUsuarioDomainEntities);

        List<ListarUsuariosServiceDTO> resultTest = listarUsuariosUseCase.execute();

        assertEquals(2, resultTest.size());
    }

}
