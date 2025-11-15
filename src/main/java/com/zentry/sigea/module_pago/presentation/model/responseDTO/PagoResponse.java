package com.zentry.sigea.module_pago.presentation.model.responseDTO;

public class PagoResponse {
    private String preferenceId;
    private String initPoint;
    private String sandboxInitPoint;
    
    public static PagoResponseBuilder builder() {
        return new PagoResponseBuilder();
    }
    
    // Getters y setters
    public String getPreferenceId() { return preferenceId; }
    public void setPreferenceId(String preferenceId) { this.preferenceId = preferenceId; }
    
    public String getInitPoint() { return initPoint; }
    public void setInitPoint(String initPoint) { this.initPoint = initPoint; }
    
    public String getSandboxInitPoint() { return sandboxInitPoint; }
    public void setSandboxInitPoint(String sandboxInitPoint) { this.sandboxInitPoint = sandboxInitPoint; }
    
    static class PagoResponseBuilder {
        private PagoResponse response = new PagoResponse();
        
        public PagoResponseBuilder preferenceId(String id) {
            response.preferenceId = id;
            return this;
        }
        
        public PagoResponseBuilder initPoint(String url) {
            response.initPoint = url;
            return this;
        }
        
        public PagoResponseBuilder sandboxInitPoint(String url) {
            response.sandboxInitPoint = url;
            return this;
        }
        
        public PagoResponse build() {
            return response;
        }
    }
}
