package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String idRefreshToken;
    private Boolean correoVerificado;
}
