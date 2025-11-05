package com.zentry.sigea.module_usuarios.infrastructure.database.embedded;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioRolId implements Serializable {
    private UUID idUsuario;
    private UUID idRol;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UsuarioRolId that)) return false;
        return idUsuario.equals(that.idUsuario) && idRol.equals(that.idRol);
    }

    @Override
    public int hashCode(){
        return java.util.Objects.hash(idUsuario , idRol);
    }

    public UUID getIdRol() {
        return idRol;
    }
    public void setIdRol(UUID idRol) {
        this.idRol = idRol;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
