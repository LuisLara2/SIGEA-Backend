package com.zentry.sigea.modules.sesiones.services.usecases;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.modules.sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListarSesionesUseCase {
    private final ISesionRepository repository;

    public ListarSesionesUseCase(ISesionRepository repository) {
        this.repository = repository;
    }

    public List<Sesion> execute() {
        return repository.findAll();
    }

    public Optional<Sesion> obtenerPorId(Long id) {
        return repository.findById(id);
    }
}

