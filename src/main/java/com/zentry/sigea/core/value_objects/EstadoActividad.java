package com.zentry.sigea.core.value_objects;

public record EstadoActividad(Long id, String codigo, String etiqueta) {

    public EstadoActividad {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío");
        }
    }

}
