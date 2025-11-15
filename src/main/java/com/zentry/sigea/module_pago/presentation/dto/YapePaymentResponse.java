package com.zentry.sigea.module_pago.presentation.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response de un pago exitoso")
public class YapePaymentResponse {

    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private Boolean success;

    @Schema(description = "ID del pago en MercadoPago", example = "123456789")
    private Long paymentId;

    @Schema(description = "Estado del pago", example = "approved")
    private String status;

    @Schema(description = "Detalle del estado", example = "accredited")
    private String statusDetail;

    @Schema(description = "Monto del pago", example = "150.00")
    private BigDecimal transactionAmount;

    @Schema(description = "Fecha de creación del pago")
    private OffsetDateTime dateCreated;

    @Schema(description = "Mensaje descriptivo", example = "Pago procesado correctamente")
    private String mensaje;

    // Getters y Setters
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}