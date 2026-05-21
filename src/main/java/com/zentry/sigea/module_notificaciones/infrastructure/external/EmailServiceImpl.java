package com.zentry.sigea.module_notificaciones.infrastructure.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_notificaciones.core.entities.NotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.ports.IEmailService;
import jakarta.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService {
    @Override
    public boolean enviarCodigoVerificacion(String correo, String nombres, Integer codigo) {
        if (!emailEnabled) {
            logger.warn("Emails deshabilitados — no enviado.");
            return false;
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(correo);
            helper.setSubject("Código de Verificación - SIGEA");

            String html = String.format(
                EmailTemplates.codigoVerificacionHtml(),
                "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoUnas_xxwueu.webp",
                "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoExtensionUnas_xo0srw.webp",
                nombres,
                codigo
            );

            helper.setText(html, true);
            mailSender.send(message);
            return true;

        } catch (Exception e) {
            logger.error("Error enviando código de verificación: {}", e.getMessage(), e);
            return false;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Value("${notificaciones.email.from:noreply@sigea.com}")
    private String emailFrom;

    @Value("${notificaciones.email.enabled:false}")
    private boolean emailEnabled;

    @Value("${notificaciones.email.nombre-remitente:SIGEA - Sistema de Gestión}")
    private String nombreRemitente;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean enviar(NotificacionDomainEntity notificacion, String destinatario, String nombreDestinatario) {

        if (!emailEnabled) {
            logger.warn("Emails deshabilitados — no enviado.");
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(destinatario);
            helper.setSubject(obtenerAsunto(notificacion));


            // Obtener todos los campos relevantes de la sesión desde notificacion

            String titulo = notificacion.getMensaje();
            String ponente = "";
            String modalidad = "";
            String lugar = "";
            String fecha = notificacion.getFechaEnvio() != null ? notificacion.getFechaEnvio().toLocalDate().toString() : "";
            String horaInicio = notificacion.getFechaEnvio() != null ? notificacion.getFechaEnvio().toLocalTime().toString() : "";
            String horaFin = "";
            String descripcion = "";



            String logoUnas = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoUnas_xxwueu.webp";
            String logoExtension = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoExtensionUnas_xo0srw.webp";
            String html = String.format(
                EmailTemplates.notificacionHtml(),
                logoUnas,
                logoExtension,
                nombreDestinatario,
                titulo,
                ponente,
                modalidad,
                lugar,
                fecha,
                horaInicio,
                horaFin,
                "-",
                descripcion,
                "Sesión Académica",
                fecha
            );

            helper.setText(html, true);

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            logger.error("Error enviando correo: {}", e.getMessage(), e);
            return false;
        }
    }

    private String obtenerAsunto(NotificacionDomainEntity notificacion) {
        if (notificacion.getTipoNotificacion() == null) 
            return "SIGEA - Notificación";

        return switch (notificacion.getTipoNotificacion().getCodigo()) {
            case "INSCRIPCION" -> "SIGEA - Inscripción Confirmada";
            case "COMUNICACION" -> "SIGEA - Comunicación";
            case "PAGO" -> "SIGEA - Pago Recibido";
            case "CERTIFICADO" -> "SIGEA - Certificado Disponible";
            default -> "SIGEA - Notificación";
        };
    }

}

    // ...existing code...
