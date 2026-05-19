package com.zentry.sigea.integration_tests.module_usuarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IRolRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.GeneralResponseDTO;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Cookie;
import jakarta.transaction.Transactional;

@SpringBootTest // levanta todo el contexto real
@AutoConfigureMockMvc // para simular peticiones HTTP
@Transactional // rollback automático después de la prueba
@ActiveProfiles("test") // definir perfil de pruebas como activo
public class UsuarioIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IUsuarioRolRepository usuarioRolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRolRepository rolRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(UsuarioIntegrationTest.class);

    // Preparacion de datos (Precondiciones)
    String insertarRolParticipanteEnDb(){
        RolDomainEntity rolDomainEntity = RolDomainEntity.create(
            "PARTICIPANTE",
            "Rol de participante"
        );
        rolRepository.save(rolDomainEntity);

        String rolParticipanteId = rolRepository.findIdByNombreRol("PARTICIPANTE");
        return rolParticipanteId;
    }
    String insertarUsuarioParticipanteEnDb(UsuarioDomainEntity usuarioParaInsertar){
        String rolParticipanteId = insertarRolParticipanteEnDb();

        usuarioParaInsertar.setPasswordHash(passwordEncoder.encode(usuarioParaInsertar.getPasswordHash()));
        usuarioRepository.save(usuarioParaInsertar);

        String usuarioParticipanteId = usuarioRepository.findIdByCorreo(usuarioParaInsertar.getCorreo());

        usuarioRolRepository.save(usuarioParticipanteId, rolParticipanteId);

        return usuarioParticipanteId;
    }

    @Test
    void INT_01_registroExitosoUsuario() throws Exception {

        // Preparacion de datos
        insertarRolParticipanteEnDb();

        String requestJson = """
        {
            "dni": "12345678",
            "nombres": "Juan Carlos",
            "apellidos": "Pérez Almiro",
            "correo": "juan@gmail.com",
            "password": "Pass@123",
            "telefono": "956456098",
            "extensionTelefonica": "+51"
        }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/usuarios/participante/registrar")
            .contentType(MediaType.APPLICATION_JSON) // se define el formato del contenido
            .content(requestJson) // se envia el cuerpo de la request
        ).andExpect(MockMvcResultMatchers.status().isCreated()); // se espera un 201 CREATED

        // Buscar en la base de datos al usuario
        Optional<UsuarioDomainEntity> usuarioInDb = usuarioRepository.findByCorreo("juan@gmail.com");

        // Verificar si esta presente
        assertTrue(usuarioInDb.isPresent());

        // Verificar si el usuario presente tiene los datos
        // que definimos antes
        UsuarioDomainEntity usuarioCreado = usuarioInDb.get();

        assertEquals("Juan Carlos", usuarioCreado.getNombres());
        assertEquals("12345678", usuarioCreado.getDni());

        // Validar relacion con rol PARTICIPANTE
        List<UsuarioDomainEntity> listUsuariosParticipantes = usuarioRolRepository.findAllUsuariosByNombreRol("PARTICIPANTE");
        
        Optional<UsuarioDomainEntity> usuarioParticipanteInDb = listUsuariosParticipantes.stream()
                                                        .filter(u -> u.getCorreo().equals("juan@gmail.com"))
                                                        .findFirst();
        assertTrue(usuarioParticipanteInDb.isPresent());

        assertEquals("12345678", usuarioParticipanteInDb.get().getDni());
    }

    void INT02_eliminarUsuarioExitosamente() throws Exception{
        // Preparacion de datos
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            "Juan Carlos", 
            "Pérez Almiro", 
            "juan@gmail.com", 
            "Pass@123", 
            "12345678", 
            false, 
            "956560098", 
            "+51"
        );
        String usuarioParticipanteId = insertarUsuarioParticipanteEnDb(usuarioDomainEntity);
    
        String loginJson = """
        {
            "correo": "juan@gmail.com",
            "password": "Pass@123",
            "rememberMe": true
        }
        """;
        MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/usuarios/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loginJson)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        String responseLoginBody = loginResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        GeneralResponseDTO<Map<String , Object>> responseLogin = mapper.readValue(responseLoginBody, new TypeReference<GeneralResponseDTO<Map<String, Object>>>() {});

        var tokenJWT = responseLogin.getExtraData().get("accessToken");

        String postRequestUrl = "/api/v1/usuarios/eliminar-usuario/" + usuarioParticipanteId;
        mockMvc.perform(MockMvcRequestBuilders.delete(postRequestUrl)
            .header("Authorization", "Bearer " + tokenJWT)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        entityManager.flush();
        entityManager.clear();

        // Verificación de usuario en base de datos
        Optional<UsuarioDomainEntity> usuarioInDb = usuarioRepository.findByCorreo("juan@gmail.com");

        // Verificar que no haya ningun usuario despues de la eliminacion
        assertTrue(!usuarioInDb.isPresent());

        // Verificar que no hay roles asociados al usuario eliminado
        List<UsuarioDomainEntity> listUsuariosParticipantes = usuarioRolRepository.findAllUsuariosByNombreRol("PARTICIPANTE");
        usuarioInDb = listUsuariosParticipantes.stream()
                        .filter(u -> u.getDni().equals("12345678"))
                        .findFirst();
        assertFalse(usuarioInDb.isPresent());
    }

    @Test
    void INT03_loginUsuarioJWT() throws Exception {
        // Preparacion de datos
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            "Juan Carlos", 
            "Pérez Almiro", 
            "test@gmail.com", 
            "password123", 
            "12345678", 
            false, 
            "956560098", 
            "+51"
        );
        String usuarioParticipanteId = insertarUsuarioParticipanteEnDb(usuarioDomainEntity);
    
        String loginJson = """
        {
            "correo": "test@gmail.com",
            "password": "password123",
            "rememberMe": true
        }
        """;
        MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/usuarios/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(loginJson)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        String responseLoginBody = loginResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        GeneralResponseDTO<Map<String , Object>> responseLogin = mapper.readValue(responseLoginBody, new TypeReference<GeneralResponseDTO<Map<String, Object>>>() {});

        var tokenJWT = responseLogin.getExtraData().get("accessToken");

        assertTrue(tokenJWT != null);

        Cookie[] cookies = loginResult.getResponse().getCookies();
        Cookie refreshTokenCookie = Arrays.stream(cookies)
            .filter(c -> "refreshToken".equals(c.getName()))
            .findFirst()
            .orElse(null);

        assertNotNull(refreshTokenCookie);
    }
}
