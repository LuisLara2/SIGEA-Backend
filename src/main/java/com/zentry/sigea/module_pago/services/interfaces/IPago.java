package com.zentry.sigea.module_pago.services.interfaces;

import java.util.Map;

public interface IPago {

    Object pagarConYape(Object request);
    Object consultarPago(Object request);
    Object testEndpoint();   
    Map<String, Object> createMetadata(Map<String, Object> request);
}
