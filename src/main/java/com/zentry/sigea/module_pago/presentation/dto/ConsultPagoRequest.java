package com.zentry.sigea.module_pago.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para consultar el estado de un pago")
public class ConsultPagoRequest {

    @Schema(description = "ID del pago en MercadoPago", example = "123456789")
    private Long paymentId;

    // Getters y Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}