package com.zentry.sigea.module_usuarios.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarUsuarioRequestDTO;
import com.zentry.sigea.security.JwtUtil;

@Service
public class AuthService {
    
    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioRolRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
        IUsuarioRepository usuarioRepository,
        IUsuarioRolRepository usuarioRolRepository,
        PasswordEncoder passwordEncoder,
        JwtUtil jwtUtil
    ){
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegistrarUsuarioRequestDTO registrarUsuarioRequestDTO) throws IOException{
        UsuarioDomainEntity usuarioDomainEntity = UsuarioDomainEntity.create(
            registrarUsuarioRequestDTO.getNombres() ,
            registrarUsuarioRequestDTO.getApellidos() ,
            registrarUsuarioRequestDTO.getCorreo() , 
            passwordEncoder.encode(registrarUsuarioRequestDTO.getPassword()) , 
            registrarUsuarioRequestDTO.getTelefono() , 
            registrarUsuarioRequestDTO.getExtensionTelefonica()
        );

        usuarioRepository.save(usuarioDomainEntity);

        String registeredUsuarioId = usuarioRepository.findByCorreo(registrarUsuarioRequestDTO.getCorreo()).map(id -> id.toString()).orElse(null);

        if (registeredUsuarioId == null) {
            throw new IOException(); // Siempre debes especificar que la funciona puede arrojar un error
        }

        for(String rolId : registrarUsuarioRequestDTO.getRolId()){
            usuarioRolRepository.save(
                registeredUsuarioId , 
                rolId
            );
        }

        return "Usuario registrado con exito.";
    }

    public String login(String correo , String password){
        var usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!passwordEncoder.matches(password , usuario.getPasswordHash())){
            throw new RuntimeException("Contrasñea incorrecta.");
        }

        List<String> roles = usuarioRolRepository.findRolesByUsuarioId(usuario.getId())
            .stream()
            .map(r -> r.getNombreRol())
            .collect(Collectors.toList());

        return jwtUtil.generateToken(password, roles);
    }

}
