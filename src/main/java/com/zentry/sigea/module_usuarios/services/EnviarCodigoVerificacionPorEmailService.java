package com.zentry.sigea.module_usuarios.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_notificaciones.core.ports.IEmailService;
import com.zentry.sigea.module_usuarios.core.entities.CodigoVerificacionDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.ICodigoVerificacionRepository;

@Service
public class EnviarCodigoVerificacionPorEmailService {
    
    private final PasswordEncoder passwordEncoder;
    private final ICodigoVerificacionRepository codigoVerificacionRepository;
    private final IEmailService emailService;

    public EnviarCodigoVerificacionPorEmailService(
        PasswordEncoder passwordEncoder, 
        ICodigoVerificacionRepository codigoVerificacionRepository, 
        IEmailService emailService
    ){
        this.passwordEncoder = passwordEncoder;
        this.codigoVerificacionRepository = codigoVerificacionRepository;
        this.emailService = emailService;
    }

    public void execute(String correo , String nombres){
        System.out.println("Intentando enviar el codigo por EMAIL");
        
        if (correo.isEmpty()) {
            throw new RuntimeException("No se envio el correo del usuarios");
        }
                    
        // Enviar email
        System.out.println("Llamando a emailService.enviar() al correo especificado");
        
        int codigoVerificacion = (int) (Math.random() * 900000) + 100000;
        boolean enviado = emailService.enviarCodigoVerificacion(correo, nombres , codigoVerificacion);
        
        if (enviado) {
            System.out.println("✅ Email enviado exitosamente");
        } else {
            System.out.println("❌ emailService.enviar() retornó FALSE para el correo especificado");
        }

        CodigoVerificacionDomainEntity codigoVerificacionDomainEntity = CodigoVerificacionDomainEntity.create(
            passwordEncoder.encode(Integer.toString(codigoVerificacion)), 
            correo
        );

        codigoVerificacionRepository.save(codigoVerificacionDomainEntity);
    }
}
