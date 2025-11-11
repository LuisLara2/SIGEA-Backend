package com.zentry.sigea.modules.sesiones.services.usecases;

import com.zentry.sigea.modules.sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Service;

@Service
public class EliminarSesionUseCase {
    private final ISesionRepository repository;

    public EliminarSesionUseCase(ISesionRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.deleteById(id);
    }
}
