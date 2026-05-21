package com.zentry.sigea.unitary_tests.module_usuarios.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.entities.TokenUsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.LoginResponseDTO;
import com.zentry.sigea.module_usuarios.services.TokenUsuarioService;
import com.zentry.sigea.module_usuarios.services.UsuarioService;
import com.zentry.sigea.security.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IUsuarioRolRepository usuarioRolRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private TokenUsuarioService tokenUsuarioService;
    
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void loginConCredencialesValidas(){
        String correo = "juan@gmail.com";
        String password = "Pass@123";
        Boolean rememberMe = true;

        UsuarioDomainEntity userInDb = UsuarioDomainEntity.create(
            "Juan", 
            "Perez", 
            "juan@gmail.com", 
            "hashedPassword", 
            "11223344", 
            true, 
            "900800900", 
            "+51"
        );
        userInDb.setId("123e4567-e89b-12d3-a456-426614174000");
        List<RolDomainEntity> listRolesOfUserInDb = List.of(
            RolDomainEntity.create("PARTICIPANTE", "Rol participante")
        );
        List<String> listRolesNamesOfUserInDb = listRolesOfUserInDb
                                                    .stream()
                                                    .map(r -> r.getNombreRol())
                                                    .collect(Collectors.toList());
        TokenUsuarioDomainEntity tokenUsuarioDomainEntity = new TokenUsuarioDomainEntity();
        tokenUsuarioDomainEntity.setExpiryDate(Instant.now().plus(24 , ChronoUnit.HOURS));
        tokenUsuarioDomainEntity.setToken("refreshTokenUsuario");
        tokenUsuarioDomainEntity.setUsuarioId("123e4567-e89b-12d3-a456-426614174000");
        tokenUsuarioDomainEntity.setId("4a1b43e2-1183-4ad4-a3de-c2da787ae56a");

        LoginResponseDTO expectedTestResult = new LoginResponseDTO();
        expectedTestResult.setAccessToken("accessTokenUsuario");
        expectedTestResult.setCorreoVerificado(userInDb.getCorreoVerificado());
        expectedTestResult.setRefreshToken("refreshTokenUsuario");
        expectedTestResult.setIdRefreshToken("4a1b43e2-1183-4ad4-a3de-c2da787ae56a");

        doReturn(Optional.of(userInDb))
            .when(usuarioRepository)
            .findByCorreo(correo);
        when(passwordEncoder.matches(password , userInDb.getPasswordHash()))
            .thenReturn(true);
        when(usuarioRolRepository.findRolesByUsuarioId(userInDb.getId()))
            .thenReturn(listRolesOfUserInDb);
        when(jwtUtil.generateToken(userInDb.getCorreo(), userInDb.getId() , listRolesNamesOfUserInDb))
            .thenReturn("accessTokenUsuario");
        when(tokenUsuarioService.createRefreshToken(userInDb.getId(), rememberMe))
            .thenReturn(tokenUsuarioDomainEntity);
        
        LoginResponseDTO realTestResut = usuarioService.login(correo, password, rememberMe);

        // Comprobacion
        verify(usuarioRepository).findByCorreo(correo);
        verify(passwordEncoder).matches(password, userInDb.getPasswordHash());
        verify(usuarioRolRepository).findRolesByUsuarioId(userInDb.getId());
        verify(jwtUtil).generateToken(userInDb.getCorreo(), userInDb.getId(), listRolesNamesOfUserInDb);
        verify(tokenUsuarioService).createRefreshToken(userInDb.getId(), rememberMe);

        assertEquals(expectedTestResult.getAccessToken(), realTestResut.getAccessToken());
        assertEquals(expectedTestResult.getRefreshToken(), realTestResut.getRefreshToken());
        assertEquals(expectedTestResult.getIdRefreshToken(), realTestResut.getIdRefreshToken());
        assertEquals(expectedTestResult.getCorreoVerificado(), realTestResut.getCorreoVerificado());
    }

}
