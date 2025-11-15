package com.zentry.sigea.module_usuarios.services.interfaces;

public interface IUsuarioService {
    Object pagarConYape(Object request);
    Object consultarPago(Object request);
    Object testEndpoint();
}
