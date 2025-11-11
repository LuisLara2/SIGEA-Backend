package com.zentry.sigea.modules.sesiones.core.services;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.modules.sesiones.core.repositories.ISesionRepository;

import java.util.List;

public class SesionDomainService {

    private final ISesionRepository repository;

    public SesionDomainService(ISesionRepository repository) {
        this.repository = repository;
    }
    // Servicio de dominio: orquesta operaciones simples (create, list) entre use-cases y repositorio.
    public Sesion crear(Sesion s) {
        return repository.save(s);
    }

    // Lista todas las sesiones.
    public List<Sesion> listar() {
        return repository.findAll();
    }
}
