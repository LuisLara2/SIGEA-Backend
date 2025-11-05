package com.zentry.sigea.module_actividad.presentation.models.responseDTO;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;

public class EstadoActividadResponse {
    private String codigo;
    private String etiqueta;

    public EstadoActividadResponse() {
    }

    public EstadoActividadResponse(String codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }


    public static EstadoActividadResponse fromEntity(EstadoActividadDomainEntity estadoActividad) {
        return new EstadoActividadResponse(
            estadoActividad.getCodigo(),
            estadoActividad.getEtiqueta()
        );
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
}
