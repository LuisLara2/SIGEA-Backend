package com.zentry.sigea.module_pago.services.interfaces;

import java.math.BigDecimal;
import java.util.Map;

public interface IPago {

    Object pagarConYape();
    Object crearUrlPago(String titulo, String descripcion, BigDecimal monto, String emailUsuario);
    Object consultarPago(Object request);
    Object testEndpoint();   
    Map<String, Object> createMetadata(Map<String, Object> request);
}
