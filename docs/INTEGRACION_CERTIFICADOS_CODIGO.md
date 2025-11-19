# 🔌 Código de Integración para Módulo de Certificados

## 📋 Instrucciones para el Desarrollador

Este documento contiene el código exacto que debes copiar y pegar en el módulo de certificados para enviar notificaciones automáticas.

---

## 1️⃣ PASO 1: Inyectar el ApplicationEventPublisher

En tu archivo **`CertificadoService.java`**, agrega esta dependencia:

```java
import org.springframework.context.ApplicationEventPublisher;
import com.zentry.sigea.module_notificaciones.events.domain.CertificadoGeneradoEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CertificadoService {
    
    private final CertificadoRepository certificadoRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final ActividadRepository actividadRepository;
    private final EstadoCertificadoRepository estadoCertificadoRepository;
    
    // ⬇️ AGREGAR ESTA LÍNEA
    private final ApplicationEventPublisher eventPublisher;
    
    // ... resto de tu código
}
```

---

## 2️⃣ PASO 2: Código para Generar Certificado y Enviar Notificación

Copia este método completo en tu **`CertificadoService.java`**:

```java
/**
 * Genera un certificado y envía notificación automática al usuario
 * @param asistenciaId ID de la asistencia del usuario
 * @return CertificadoEntity generado
 */
@Transactional
public CertificadoEntity generarCertificado(String asistenciaId) {
    // 1. Obtener la asistencia
    AsistenciaEntity asistencia = asistenciaRepository.findById(Long.parseLong(asistenciaId))
        .orElseThrow(() -> new EntityNotFoundException("Asistencia no encontrada"));
    
    // 2. Obtener la actividad relacionada
    ActividadEntity actividad = actividadRepository.findById(asistencia.getActividadId())
        .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada"));
    
    // 3. Generar código de validación único
    String codigoValidacion = generarCodigoValidacionUnico();
    
    // 4. Obtener estado GENERADO
    EstadoCertificadoEntity estadoGenerado = estadoCertificadoRepository
        .findByCodigo("GENERADO")
        .orElseThrow(() -> new RuntimeException("Estado GENERADO no encontrado"));
    
    // 5. Crear el certificado
    CertificadoEntity certificado = new CertificadoEntity();
    certificado.setAsistencia(asistencia);
    certificado.setCodigoValidacion(codigoValidacion);
    certificado.setFechaEmision(LocalDate.now());
    certificado.setEstadoCertificado(estadoGenerado);
    certificado.setCreatedAt(LocalDateTime.now());
    certificado.setUpdatedAt(LocalDateTime.now());
    
    certificado = certificadoRepository.save(certificado);
    
    // 6. Generar PDF (opcional - puede ser null)
    String urlPdf = null;
    try {
        // Si tienes servicio de generación de PDF, úsalo aquí
        // urlPdf = pdfGeneratorService.generarCertificadoPdf(certificado);
        // Por ahora dejamos null, el PDF se generará después
    } catch (Exception e) {
        logger.error("Error generando PDF: {}", e.getMessage());
    }
    
    certificado.setUrlPdf(urlPdf);
    certificado = certificadoRepository.save(certificado);
    
    // ⬇️⬇️⬇️ CÓDIGO DE NOTIFICACIÓN - COPIAR ESTO ⬇️⬇️⬇️
    // 7. Publicar evento de notificación
    eventPublisher.publishEvent(new CertificadoGeneradoEvent(
        asistencia.getUsuarioId().toString(),              // ID del usuario
        certificado.getId().toString(),                    // ID del certificado
        actividad.getId().toString(),                      // ID de la actividad
        actividad.getTitulo(),                             // Título de la actividad
        certificado.getCodigoValidacion(),                 // Código de validación
        certificado.getFechaEmision(),                     // Fecha de emisión
        CertificadoGeneradoEvent.EstadoCertificado.GENERADO, // Estado
        certificado.getUrlPdf(),                           // URL del PDF (puede ser null)
        asistencia.getId().toString(),                     // ID de asistencia
        LocalDateTime.now()                                // Fecha de generación
    ));
    // ⬆️⬆️⬆️ FIN CÓDIGO DE NOTIFICACIÓN ⬆️⬆️⬆️
    
    logger.info("✅ Certificado generado y notificación enviada - Usuario: {}", 
        asistencia.getUsuarioId());
    
    return certificado;
}

/**
 * Genera un código de validación único de 20 caracteres
 */
private String generarCodigoValidacionUnico() {
    String codigo;
    do {
        codigo = UUID.randomUUID().toString()
            .replace("-", "")
            .substring(0, 20)
            .toUpperCase();
    } while (certificadoRepository.existsByCodigoValidacion(codigo));
    
    return codigo;
}
```

---

## 3️⃣ PASO 3: Si Cambias el Estado del Certificado (Ejemplo: EMITIDO)

Si tienes un método para emitir oficialmente el certificado, agrega esto:

```java
/**
 * Emite oficialmente un certificado y notifica al usuario
 * @param certificadoId ID del certificado
 * @return CertificadoEntity actualizado
 */
@Transactional
public CertificadoEntity emitirCertificado(String certificadoId) {
    // 1. Obtener el certificado
    CertificadoEntity certificado = certificadoRepository.findById(Long.parseLong(certificadoId))
        .orElseThrow(() -> new EntityNotFoundException("Certificado no encontrado"));
    
    // 2. Cambiar estado a EMITIDO
    EstadoCertificadoEntity estadoEmitido = estadoCertificadoRepository
        .findByCodigo("EMITIDO")
        .orElseThrow(() -> new RuntimeException("Estado EMITIDO no encontrado"));
    
    certificado.setEstadoCertificado(estadoEmitido);
    certificado.setUpdatedAt(LocalDateTime.now());
    certificado = certificadoRepository.save(certificado);
    
    // 3. Obtener datos relacionados
    AsistenciaEntity asistencia = certificado.getAsistencia();
    ActividadEntity actividad = actividadRepository.findById(asistencia.getActividadId())
        .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada"));
    
    // ⬇️⬇️⬇️ CÓDIGO DE NOTIFICACIÓN - COPIAR ESTO ⬇️⬇️⬇️
    // 4. Publicar evento de emisión
    eventPublisher.publishEvent(new CertificadoGeneradoEvent(
        asistencia.getUsuarioId().toString(),
        certificado.getId().toString(),
        actividad.getId().toString(),
        actividad.getTitulo(),
        certificado.getCodigoValidacion(),
        certificado.getFechaEmision(),
        CertificadoGeneradoEvent.EstadoCertificado.EMITIDO,  // ← Estado EMITIDO
        certificado.getUrlPdf(),
        asistencia.getId().toString(),
        LocalDateTime.now()
    ));
    // ⬆️⬆️⬆️ FIN CÓDIGO DE NOTIFICACIÓN ⬆️⬆️⬆️
    
    logger.info("✅ Certificado emitido y notificación enviada - Usuario: {}", 
        asistencia.getUsuarioId());
    
    return certificado;
}
```

---

## 4️⃣ PASO 4: Imports Necesarios

Asegúrate de tener estos imports en tu archivo:

```java
// Imports estándar de Java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// Imports de Spring
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Import del evento de notificación
import com.zentry.sigea.module_notificaciones.events.domain.CertificadoGeneradoEvent;

// Tus imports de entidades
import com.zentry.sigea.module_certificados.entities.CertificadoEntity;
import com.zentry.sigea.module_certificados.entities.EstadoCertificadoEntity;
// ... resto de tus imports
```

---

## 5️⃣ PASO 5: Estados en Base de Datos

Ejecuta este SQL si aún no tienes los estados:

```sql
-- Insertar estados de certificados si no existen
INSERT INTO estado_certificado (codigo, etiqueta) 
VALUES 
    ('GENERADO', 'Certificado Generado'),
    ('EMITIDO', 'Certificado Emitido'),
    ('VALIDADO', 'Certificado Validado'),
    ('ANULADO', 'Certificado Anulado')
ON CONFLICT (codigo) DO NOTHING;
```

---

## 6️⃣ EJEMPLO COMPLETO DE USO

### Cuando un usuario completa el curso:

```java
@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {
    
    private final CertificadoService certificadoService;
    
    @PostMapping("/generar")
    public ResponseEntity<CertificadoDTO> generarCertificado(
            @RequestParam String asistenciaId) {
        
        // Generar certificado (esto automáticamente envía la notificación)
        CertificadoEntity certificado = certificadoService.generarCertificado(asistenciaId);
        
        // El usuario recibirá:
        // 1. Notificación en sistema (BD)
        // 2. Email: "🎓 ¡Felicidades! Tu certificado está listo"
        
        return ResponseEntity.ok(convertToDTO(certificado));
    }
}
```

---

## 7️⃣ MAPEO DE ESTADOS

Usa estos valores en el enum según el estado del certificado:

```java
// Estado GENERADO (certificado recién creado)
CertificadoGeneradoEvent.EstadoCertificado.GENERADO

// Estado EMITIDO (certificado oficialmente emitido)
CertificadoGeneradoEvent.EstadoCertificado.EMITIDO

// Estado VALIDADO (certificado verificado)
CertificadoGeneradoEvent.EstadoCertificado.VALIDADO

// Estado ANULADO (certificado cancelado)
CertificadoGeneradoEvent.EstadoCertificado.ANULADO
```

---

## 8️⃣ CONTENIDO DEL EMAIL QUE RECIBIRÁ EL USUARIO

Cuando ejecutes el código anterior, el usuario recibirá este email:

```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: usuario@email.com
Asunto: SIGEA - Tu Certificado esta Listo

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: [Nombre de la Actividad]
📋 Estado: Certificado generado
📅 Fecha de emisión: 17/11/2025

🔐 Código de validación:
   K7M3P9Q2X5W8A1N4Y6T2

📥 Descarga tu certificado:
   [URL del PDF o mensaje de "pronto disponible"]

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉
```

---

## ✅ CHECKLIST DE INTEGRACIÓN

Marca cada paso cuando lo completes:

- [ ] Agregué `ApplicationEventPublisher` a mi servicio
- [ ] Importé `CertificadoGeneradoEvent`
- [ ] Copié el método `generarCertificado()` completo
- [ ] Copié el método `generarCodigoValidacionUnico()`
- [ ] Agregué el código de `eventPublisher.publishEvent()` después de guardar el certificado
- [ ] Verifiqué que los estados existan en la base de datos
- [ ] Probé generando un certificado
- [ ] Verifiqué que llegue el email al usuario
- [ ] Verifiqué que la notificación se guarde en la BD

---

## 🚨 ERRORES COMUNES

### Error 1: "Cannot resolve symbol CertificadoGeneradoEvent"
**Solución**: Agrega el import:
```java
import com.zentry.sigea.module_notificaciones.events.domain.CertificadoGeneradoEvent;
```

### Error 2: "Estado GENERADO no encontrado"
**Solución**: Ejecuta el SQL del Paso 5 para insertar los estados.

### Error 3: "No suitable constructor found"
**Solución**: Verifica que pasas los 10 parámetros en el orden correcto:
1. usuarioId (String)
2. certificadoId (String)
3. actividadId (String)
4. actividadTitulo (String)
5. codigoValidacion (String)
6. fechaEmision (LocalDate)
7. estado (EstadoCertificado enum)
8. urlPdf (String - puede ser null)
9. asistenciaId (String)
10. fechaGeneracion (LocalDateTime)

---

## 💡 NOTAS IMPORTANTES

1. **El código se ejecuta de forma asíncrona**: No bloquea la generación del certificado
2. **Tolerante a fallos**: Si falla el email, el certificado igual se genera
3. **urlPdf puede ser null**: Si el PDF no está listo, el email dirá "pronto disponible"
4. **Código de validación único**: El método `generarCodigoValidacionUnico()` garantiza que no se repita
5. **Usuario recibe 2 notificaciones**: Una en BD (siempre) + una por email (si SMTP funciona)

---

## 📞 ¿NECESITAS AYUDA?

Si tienes problemas:
1. Verifica los logs del servidor (busca emojis 🎓 ✅ ❌)
2. Revisa que `application.properties` tenga la config de email
3. Verifica que el módulo de notificaciones esté corriendo
4. Consulta `NOTIFICACIONES_CERTIFICADOS.md` para más ejemplos

---

**Fecha**: 17 de noviembre de 2025  
**Versión**: 1.0  
**Estado**: ✅ Listo para integrar
