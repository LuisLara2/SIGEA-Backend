package com.zentry.sigea.infrastructure.database.embedded;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioRolId implements Serializable {
    private Long idUsuario;
    private Long idRol;

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
}
