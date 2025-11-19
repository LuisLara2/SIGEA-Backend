package com.zentry.sigea.module_certificacion.services.serviceDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearValidacionRequestServiceDTO {
    public String certificadoId;
    public String tipoValidadorId;
    public String resultado;
    public String detalle;
}
