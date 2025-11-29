package com.zentry.sigea.module_usuarios.jobs;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.repositories.ITokenUsuarioRepository;

@Component
public class TokenUsuarioCleanupTask {
    private final ITokenUsuarioRepository tokenUsuarioRepository;

    public TokenUsuarioCleanupTask(ITokenUsuarioRepository tokenUsuarioService) {
        this.tokenUsuarioRepository = tokenUsuarioService;
    }

    // Ejecuta cada hora
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void limpiarTokensExpirados() {
        Instant now = Instant.now();

        tokenUsuarioRepository.deleteExpiredTokens(now);
    }
}
