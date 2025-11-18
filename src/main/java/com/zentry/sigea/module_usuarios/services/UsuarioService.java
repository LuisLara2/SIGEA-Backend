package com.zentry.sigea.module_usuarios.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.security.JwtUtil;

@Service
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioRolRepository usuarioRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(
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

        return jwtUtil.generateToken(
            usuario.getCorreo(), 
            usuario.getId() ,
            roles
        );
    }
}