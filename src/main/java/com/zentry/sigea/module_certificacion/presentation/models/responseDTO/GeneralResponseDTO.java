package com.zentry.sigea.module_certificacion.presentation.models.responseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponseDTO<T> {
    private boolean status;
    private String message;
    private T extraData;

    public GeneralResponseDTO(
        boolean status,
        String message,
        T extraData
    ){
        this.status = status;
        this.message = message;
        this.extraData = extraData;
    }
}