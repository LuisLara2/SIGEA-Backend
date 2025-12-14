package com.zentry.sigea.module_usuarios.services.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRepository;

@Component
public class EditarUsuarioInformationUseCase {
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public EditarUsuarioInformationUseCase(
        IUsuarioRepository usuarioRepository , 
        PasswordEncoder passwordEncoder
    ){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(UsuarioDomainEntity usuarioDomainEntity){

        String userPasswordHash = usuarioRepository.findPasswordHashById(usuarioDomainEntity.getId());

        Boolean passwordMatches = passwordEncoder.matches(usuarioDomainEntity.getPasswordHash(), userPasswordHash);

        if(passwordMatches){
            usuarioDomainEntity.setPasswordHash(passwordEncoder.encode(usuarioDomainEntity.getPasswordHash()));
        }

        usuarioRepository.update(usuarioDomainEntity, passwordMatches);

        return "Usuario actualizado correctamente";
    }
}
