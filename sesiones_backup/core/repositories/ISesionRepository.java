package com.zentry.sigea.modules.sesiones.core.repositories;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import java.util.List;
import java.util.Optional;

public interface ISesionRepository {
    Sesion save(Sesion sesion);
    Optional<Sesion> findById(Long id);
    List<Sesion> findAll();
    void deleteById(Long id);
}
