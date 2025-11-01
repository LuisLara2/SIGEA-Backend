package com.zentry.sigea.module_actividad.core.entities.actividad;

public class EstadoActividad {
    private final Long id;
    private final String codigo;
    private final String etiqueta;

    public EstadoActividad(Long id, String codigo, String etiqueta) {
        this.id = id;
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }


    public static EstadoActividad create(String codigo, String etiqueta) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío");
        }
        return new EstadoActividad(null, codigo, etiqueta);
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

}
