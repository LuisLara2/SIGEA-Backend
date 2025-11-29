package com.zentry.sigea.module_usuarios.jobs;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.repositories.ICodigoVerificacionRepository;

@Component
public class CodigoValidacionCleanupTask {
    private final ICodigoVerificacionRepository codigoVerificacionRepository;

    public CodigoValidacionCleanupTask(ICodigoVerificacionRepository codigoVerificacionRepository) {
        this.codigoVerificacionRepository = codigoVerificacionRepository;
    }

    // Ejecuta cada media hora
    @Scheduled(fixedRate = 60 * 60 * 500)
    public void limpiarTokensExpirados() {
        Instant now = Instant.now();

        codigoVerificacionRepository.deleteExpiresCodes(now);
    }
}
