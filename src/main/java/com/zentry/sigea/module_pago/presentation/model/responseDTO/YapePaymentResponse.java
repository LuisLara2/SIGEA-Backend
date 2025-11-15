package com.zentry.sigea.module_pago.presentation.model.responseDTO;

import java.math.BigDecimal;

public class YapePaymentResponse {
    private boolean success;
    private Long paymentId;
    private String status;          // approved, rejected, pending, etc.
    private String statusDetail;
    private BigDecimal transactionAmount;
    private java.time.OffsetDateTime dateCreated;
    private String mensaje;
    
    public static YapePaymentResponseBuilder builder() {
        return new YapePaymentResponseBuilder();
    }
    
    // Getters y Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getStatusDetail() { return statusDetail; }
    public void setStatusDetail(String statusDetail) { this.statusDetail = statusDetail; }
    
    public BigDecimal getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(BigDecimal transactionAmount) { this.transactionAmount = transactionAmount; }
    
    public java.time.OffsetDateTime getDateCreated() { return dateCreated; }
    public void setDateCreated(java.time.OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }
    
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    
    public static class YapePaymentResponseBuilder {
        private YapePaymentResponse response = new YapePaymentResponse();
        
        public YapePaymentResponseBuilder success(boolean success) {
            response.success = success;
            return this;
        }
        
        public YapePaymentResponseBuilder paymentId(Long id) {
            response.paymentId = id;
            return this;
        }
        
        public YapePaymentResponseBuilder status(String status) {
            response.status = status;
            return this;
        }
        
        public YapePaymentResponseBuilder statusDetail(String detail) {
            response.statusDetail = detail;
            return this;
        }
        
        public YapePaymentResponseBuilder transactionAmount(BigDecimal amount) {
            response.transactionAmount = amount;
            return this;
        }
        
        public YapePaymentResponseBuilder dateCreated(java.time.OffsetDateTime date) {
            response.dateCreated = date;
            return this;
        }
        
        public YapePaymentResponseBuilder mensaje(String mensaje) {
            response.mensaje = mensaje;
            return this;
        }
        
        public YapePaymentResponse build() {
            return response;
        }
    }
}