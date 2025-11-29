package com.zentry.sigea.module_usuarios.core.entities;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CodigoVerificacionDomainEntity {
    private String codigo;
    private String correo;
    private Instant expiresAt;

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public static CodigoVerificacionDomainEntity create(
        String codigo , 
        String correo
    ){
        CodigoVerificacionDomainEntity codigoVerificacionDomainEntity = new CodigoVerificacionDomainEntity();

        Instant expiresAt = Instant.now().plus(5 , ChronoUnit.MINUTES);

        codigoVerificacionDomainEntity.setCodigo(codigo);
        codigoVerificacionDomainEntity.setCorreo(correo);
        codigoVerificacionDomainEntity.setExpiresAt(expiresAt);

        return codigoVerificacionDomainEntity;
    }
}
