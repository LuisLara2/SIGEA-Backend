package com.zentry.sigea.modules.sesiones.services.usecases;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.modules.sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActualizarSesionUseCase {
    private final ISesionRepository repository;

    public ActualizarSesionUseCase(ISesionRepository repository) {
        this.repository = repository;
    }

    public Optional<Sesion> execute(Long id, Sesion updated) {
        return repository.findById(id).map(existing -> {
            // create domain object with same id
            Sesion toSave = Sesion.withId(id, updated.getActividadId(), updated.getFechaSesion(), updated.getTitulo());
            return repository.save(toSave);
        });
    }
}
