package com.zentry.sigea.module_usuarios.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.core.repositories.ICodigoVerificacionRepository;

@Service
public class ValidarCodigoEnviadoService {
    
    private final ICodigoVerificacionRepository codigoVerificacionRepository;
    private final PasswordEncoder passwordEncoder;

    public ValidarCodigoEnviadoService(
        ICodigoVerificacionRepository codigoVerificacionRepository,
        PasswordEncoder passwordEncoder
    ){
        this.codigoVerificacionRepository = codigoVerificacionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean execute(String codigo , String correo){

        List<String> listCodigosPorCorreo = codigoVerificacionRepository.findCodigoByCorreo(correo);

        for(String codigoVerficiacion : listCodigosPorCorreo){
            if(passwordEncoder.matches(codigo , codigoVerficiacion)){
                codigoVerificacionRepository.deleteAllByCorreo(correo);
                return true;
            }
        }

        return false;
    }
}
