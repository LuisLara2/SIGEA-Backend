package com.zentry.sigea.module_usuarios.core.repositories;

import java.util.List;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;

public interface IUsuarioRolRepository {
    public List<RolDomainEntity> findRolesByUsuarioId(String usuarioId);
    public String findAsingandoEnByUsuarioIdAndRolId(String usuarioId , String rolId);
    // Estos nombres son confusos pero significan lo siguiente
    // El primero: SELECT * FROM usuario_rol WHERE usuario_id = ?;
    // EL Segundo: SELECT asignado_en FROM usuario_rol WHERE usuario_id = ? AND rol_id = ?;
}
