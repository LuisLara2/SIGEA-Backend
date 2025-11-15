package com.zentry.sigea.module_pago.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response de error en operaciones de pago")
public class ErrorResponse {

    @Schema(description = "Indica que la operación falló", example = "false")
    private Boolean success;

    @Schema(description = "Código de error", example = "API_ERROR")
    private String error;

    @Schema(description = "Mensaje descriptivo del error", example = "Error al procesar el pago")
    private String mensaje;

    @Schema(description = "Código de estado HTTP (opcional)", example = "400")
    private Integer statusCode;

    // Getters y Setters
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}