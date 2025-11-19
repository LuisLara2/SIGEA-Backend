package com.zentry.sigea.module_certificacion.services.serviceDTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearValidacionItemResponseServiceDTO {
    private String actividadId;
    private String titulo;
    private String descripcion;

    private List<CrearValidacionItemResponseServiceDTO> listCrearValidacionItemResponseServiceDTOs;
}
