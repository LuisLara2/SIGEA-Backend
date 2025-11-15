package com.zentry.sigea.module_pago.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para el endpoint de prueba")
public class TestEndpointRequest {

    @Schema(description = "Monto de prueba", example = "100.00")
    private String amount;

    // Getters y Setters
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}