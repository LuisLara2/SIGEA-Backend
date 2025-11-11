package com.zentry.sigea.modules.sesiones.infrastructure.repositories;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.modules.sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria del repositorio de Sesion.
 * Mantiene independencia del módulo Actividad mientras éste no sea integrado.
 */
@Repository
public class SesionRepositoryImpl implements ISesionRepository {

    private final List<Sesion> inMemory = new ArrayList<>();
    private long seq = 1L;

    @Override
    public Sesion save(Sesion s) {
        if (s.getId() == null) {
            s.setId(seq++);
            inMemory.add(s);
            return s;
        }
        // update existing: remove previous with same id and add updated
        inMemory.removeIf(x -> x.getId() != null && x.getId().equals(s.getId()));
        inMemory.add(s);
        return s;
    }

    @Override
    public Optional<Sesion> findById(Long id) {
        return inMemory.stream().filter(x -> x.getId() != null && x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Sesion> findAll() {
        return new ArrayList<>(inMemory);
    }

    @Override
    public void deleteById(Long id) {
        inMemory.removeIf(x -> x.getId() != null && x.getId().equals(id));
    }
}
