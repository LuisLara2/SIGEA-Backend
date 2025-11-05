package com.zentry.sigea.module_actividad.presentation.models.responseDTO;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;

public class TipoActividadResponse {
    private String nombreActividad;
    private String descripcion;

    public TipoActividadResponse() {
    }

    public TipoActividadResponse(String nombreActividad, String descripcion) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
    }


    /**
     * Factory method para crear un TipoActividadResponse desde una entidad TipoActividad
     */

    public static TipoActividadResponse fromEntity(TipoActividadDomainEntity tipoActividad) {
        
        return new TipoActividadResponse(
            tipoActividad.getNombreActividad(),
            tipoActividad.getDescripcion()
        );
    }
    public String getNombreActividad() {
        return nombreActividad;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
