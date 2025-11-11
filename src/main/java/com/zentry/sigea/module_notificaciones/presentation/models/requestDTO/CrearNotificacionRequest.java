package com.zentry.sigea.module_notificaciones.presentation.models.requestDTO;

/**
 * DTO para recibir datos de creación de notificación desde el frontend
 */
public class CrearNotificacionRequest {
    private String usuarioId;
    private String actividadId; // Opcional
    private String tipoNotificacionId;
    private String mensaje;
    private String estadoNotificacionId;

    // Constructor vacío para Jackson
    public CrearNotificacionRequest() {}

    public CrearNotificacionRequest(
        String usuarioId,
        String actividadId,
        String tipoNotificacionId,
        String mensaje,
        String estadoNotificacionId
    ) {
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
        this.tipoNotificacionId = tipoNotificacionId;
        this.mensaje = mensaje;
        this.estadoNotificacionId = estadoNotificacionId;
    }

    // Getters y Setters
    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public String getTipoNotificacionId() {
        return tipoNotificacionId;
    }

    public void setTipoNotificacionId(String tipoNotificacionId) {
        this.tipoNotificacionId = tipoNotificacionId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstadoNotificacionId() {
        return estadoNotificacionId;
    }

    public void setEstadoNotificacionId(String estadoNotificacionId) {
        this.estadoNotificacionId = estadoNotificacionId;
    }
}
