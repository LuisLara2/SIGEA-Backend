package com.zentry.sigea.module_pago.presentation.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para crear un pago con Yape")
public class YapePaymentRequest {

    @Schema(description = "Descripción del pago", example = "Pago por inscripción a evento")
    private String descripcion;

    @Schema(description = "Monto del pago", example = "150.00")
    private BigDecimal monto;

    @Schema(description = "Token de Yape obtenido del frontend", example = "ff8080814c11e237014c1ff593b57b4d")
    private String token;

    @Schema(description = "Email del pagador", example = "usuario@ejemplo.com")
    private String email;

    @Schema(description = "Referencia interna del sistema", example = "REF-2025-001")
    private String referencia;

    @Schema(description = "ID del usuario (opcional)", example = "123")
    private Long usuarioId;

    // Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}