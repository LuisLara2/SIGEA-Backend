package com.zentry.sigea.module_usuarios.services.usecases.administrator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.services.usecases.administrador.RegisterUsuarioUseCase;

@ExtendWith(MockitoExtension.class)
public class RegisterUsuarioUseCaseTest {
    
    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private IUsuarioRolRepository usuarioRolRepository;

    @InjectMocks
    private RegisterUsuarioUseCase registerUsuarioUseCase;

    @Test
    public void registrarUsuarioExitosamente() throws IOException {
        // Arrange - Preparar objetos o datos
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            "Juan Carlos", 
            "Pérez Almiro", 
            "juan@gmail.com", 
            "Pass@123", 
            "12345678", 
            false, 
            "956456098", 
            "+51"
        );

        List<String> listRolesId = List.of("ROL_PARTICIPANTE_ID");

        when(passwordEncoder.encode("Pass@123")).thenReturn("hashedPassword");
        when(usuarioRepository.findIdByCorreo("juan@gmail.com")).thenReturn("user-id-123");
        
        // Act
        String result = registerUsuarioUseCase.execute(usuarioDomainEntity, listRolesId);

        // Assert
        assertEquals("Usuario registrado con exito.", result);

        verify(passwordEncoder).encode("Pass@123");
        verify(usuarioRepository).save(usuarioDomainEntity);
        verify(usuarioRepository).findIdByCorreo("juan@gmail.com");
        verify(usuarioRolRepository).saveOneUserWithAllRolesId("user-id-123", listRolesId);

        assertEquals("hashedPassword", usuarioDomainEntity.getPasswordHash());
    }

    @Test
    public void registrarUsuarioConDniDuplicado(){
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            "Ana", 
            "Lopez", 
            "ana@gmail.com", 
            "Pass@123", 
            "12345678", 
            false, 
            "956456098", 
            "+51"
        );
        List<String> listRolesId = List.of("ROL_PARTICIPANTE_ID");

        when(passwordEncoder.encode("Pass@123")).thenReturn("hashedPassword");
        // Cuando simulas el comportamiento de un metodo que devuelve void
        // usamos esta sintaxis
        doThrow(new DataIntegrityViolationException("constraint"))
            .when(usuarioRepository)
            .save(any());

        DataIntegrityViolationException exception = assertThrows(
            DataIntegrityViolationException.class,
            () -> registerUsuarioUseCase.execute(usuarioDomainEntity, listRolesId)
        );

        assertEquals("Error: El DNI ya está registrado", exception.getMessage());
        System.out.println(exception.getMessage());

        verify(usuarioRolRepository, never())
            .saveOneUserWithAllRolesId(any(), any());
    }

    @Test
    public void registrarUsuarioSinCorreoNiCelular(){
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            "Ana", 
            "Lopez", 
            "ana@gmail.com", 
            "Pass@123", 
            "12345678", 
            false, 
            "956456098", 
            "+51"
        );
        usuarioDomainEntity.setTelefono(null);
        usuarioDomainEntity.setCorreo(null);
        List<String> listRolesId = List.of("ROL_PARTICIPANTE_ID");

        when(passwordEncoder.encode("Pass@123")).thenReturn("hashedPassword");
        doThrow(new DataIntegrityViolationException("constraint"))
            .when(usuarioRepository)
            .save(usuarioDomainEntity);

        DataIntegrityViolationException exception = assertThrows(
            DataIntegrityViolationException.class, 
            () -> registerUsuarioUseCase.execute(usuarioDomainEntity, listRolesId)
        );

        assertEquals("Error de validacion: Campo de Contacto obligatorio", exception.getMessage());

        verify(usuarioRolRepository , never())
            .saveOneUserWithAllRolesId(any(), any());
    }
}
