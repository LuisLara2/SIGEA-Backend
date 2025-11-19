package com.zentry.sigea.module_certificacion.presentation.models.requestDTO;

import lombok.Getter;

@Getter
public class CrearValidacionRequestDTO {
    public String certificadoId;
    public String tipoValidadorId;
    public String resultado;
    public String detalle;
}
