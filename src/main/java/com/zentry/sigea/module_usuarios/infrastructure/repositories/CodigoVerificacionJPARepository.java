package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.CodigoVerificacionEntity;

public interface CodigoVerificacionJPARepository extends JpaRepository<CodigoVerificacionEntity , UUID>{
    public Optional<CodigoVerificacionEntity> findByCorreoAndCodigo(String correo , String codigo);
    
    @Query(
        """
            SELECT cv.codigo FROM CodigoVerificacionEntity cv WHERE cv.correo = :correo
        """
    )
    public List<String> findCodigoByCorreo(@Param("correo") String correo);

    @Modifying
    @Transactional
    @Query(
        """
            DELETE FROM CodigoVerificacionEntity c WHERE c.expiresAt = :now
        """
    )
    public void deleteExpiredCodes(@Param("now") Instant now);
}
