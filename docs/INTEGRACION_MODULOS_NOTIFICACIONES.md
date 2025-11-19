# Integración de Notificaciones Automáticas - Módulos Pendientes

## 📋 Resumen

Este documento explica cómo integrar el sistema de notificaciones automáticas en los módulos que aún no lo tienen implementado.

**Estado actual:**
- ✅ **Inscripciones** - Implementado
- ✅ **Actividades** - Implementado
- ✅ **Sesiones** - Implementado
- ❌ **Pagos** - Pendiente
- ❌ **Certificados** - Pendiente
- ❌ **Comunicaciones** - Pendiente

---

## 🔧 Pasos para Integrar Notificaciones

### 1️⃣ **Módulo de PAGOS**

#### Archivo a modificar: `module_pagos/services/PagoService.java`

**Paso 1:** Agregar imports necesarios

```java
import org.springframework.context.ApplicationEventPublisher;
import com.zentry.sigea.module_notificaciones.events.domain.PagoCompletadoEvent;
import java.time.LocalDateTime;
```

**Paso 2:** Agregar `ApplicationEventPublisher` como dependencia

```java
@Service
public class PagoService {
    
    // Agregar esta línea
    private final ApplicationEventPublisher eventPublisher;
    
    // Modificar el constructor para incluir eventPublisher
    public PagoService(
        // ... otras dependencias existentes
        ApplicationEventPublisher eventPublisher
    ) {
        // ... asignaciones existentes
        this.eventPublisher = eventPublisher;
    }
```

**Paso 3:** Publicar evento después de completar un pago

```java
public String procesarPago(PagoRequest request) {
    // Lógica existente de procesamiento de pago
    String pagoId = crearPagoUseCase.execute(request);
    
    // AGREGAR ESTO: Publicar evento para notificación automática
    eventPublisher.publishEvent(new PagoCompletadoEvent(
        request.getUsuarioId(),
        request.getInscripcionId(),  // o actividadId según tu modelo
        request.getMonto(),
        pagoId,
        LocalDateTime.now()
    ));
    
    return pagoId;
}
```

---

### 2️⃣ **Módulo de CERTIFICADOS**

#### Archivo a modificar: `module_certificados/services/CertificadoService.java`

**Paso 1:** Agregar imports necesarios

```java
import org.springframework.context.ApplicationEventPublisher;
import com.zentry.sigea.module_notificaciones.events.domain.CertificadoGeneradoEvent;
import java.time.LocalDateTime;
```

**Paso 2:** Agregar `ApplicationEventPublisher` como dependencia

```java
@Service
public class CertificadoService {
    
    // Agregar esta línea
    private final ApplicationEventPublisher eventPublisher;
    
    // Modificar el constructor
    public CertificadoService(
        // ... otras dependencias existentes
        ApplicationEventPublisher eventPublisher
    ) {
        // ... asignaciones existentes
        this.eventPublisher = eventPublisher;
    }
```

**Paso 3:** Publicar evento después de generar un certificado

```java
public String generarCertificado(CertificadoRequest request) {
    // Lógica existente de generación de certificado
    String certificadoId = generarCertificadoUseCase.execute(request);
    
    // AGREGAR ESTO: Publicar evento para notificación automática
    eventPublisher.publishEvent(new CertificadoGeneradoEvent(
        request.getUsuarioId(),
        request.getActividadId(),  // o inscripcionId según tu modelo
        certificadoId,
        LocalDateTime.now()
    ));
    
    return certificadoId;
}
```

---

### 3️⃣ **Módulo de COMUNICACIONES**

#### Archivo a modificar: `module_comunicaciones/services/ComunicacionService.java`

**Paso 1:** Agregar imports necesarios

```java
import org.springframework.context.ApplicationEventPublisher;
import com.zentry.sigea.module_notificaciones.events.domain.ComunicacionPublicadaEvent;
import java.time.LocalDateTime;
import java.util.List;
```

**Paso 2:** Agregar `ApplicationEventPublisher` como dependencia

```java
@Service
public class ComunicacionService {
    
    // Agregar esta línea
    private final ApplicationEventPublisher eventPublisher;
    
    // Modificar el constructor
    public ComunicacionService(
        // ... otras dependencias existentes
        ApplicationEventPublisher eventPublisher
    ) {
        // ... asignaciones existentes
        this.eventPublisher = eventPublisher;
    }
```

**Paso 3:** Publicar evento después de publicar una comunicación

```java
public String publicarComunicacion(ComunicacionRequest request) {
    // Lógica existente de publicación
    String comunicacionId = publicarComunicacionUseCase.execute(request);
    
    // AGREGAR ESTO: Publicar evento para notificación automática masiva
    eventPublisher.publishEvent(new ComunicacionPublicadaEvent(
        request.getTipoComunicacion(),  // Ej: "NUEVA_SESION", "ANUNCIO", "CANCELACION"
        request.getTitulo(),
        request.getContenido(),
        request.getUsuariosIds(),  // Lista de usuarios a notificar
        comunicacionId,
        LocalDateTime.now()
    ));
    
    return comunicacionId;
}
```

---

## 📝 Notas Importantes

### ¿Qué hace cada componente?

1. **ApplicationEventPublisher**: Clase de Spring que permite publicar eventos de dominio
2. **Event (PagoCompletadoEvent, etc.)**: Clases que encapsulan los datos del evento
3. **EventListener**: Ya implementados en `module_notificaciones/events/listeners/` - escuchan automáticamente los eventos y crean las notificaciones

### Flujo de funcionamiento

```
[Módulo] → Publica Evento → [EventListener] → Crea Notificación → [Base de Datos]
   ↓                              ↓                    ↓
PagoService          PagoEventListener      NotificacionService
```

### Ventajas de este enfoque

✅ **Desacoplamiento**: Los módulos no dependen directamente del módulo de notificaciones
✅ **Asíncrono**: Las notificaciones se procesan en segundo plano (@Async)
✅ **Escalable**: Fácil agregar más listeners sin modificar los módulos originales
✅ **Mantenible**: Cada listener maneja un solo tipo de evento (Single Responsibility)

---

## 🎯 Ejemplo Completo: Módulo de Pagos

### Antes (sin notificaciones automáticas)

```java
@Service
public class PagoService {
    
    private final CrearPagoUseCase crearPagoUseCase;
    
    public PagoService(CrearPagoUseCase crearPagoUseCase) {
        this.crearPagoUseCase = crearPagoUseCase;
    }
    
    public String procesarPago(PagoRequest request) {
        return crearPagoUseCase.execute(request);
    }
}
```

### Después (con notificaciones automáticas)

```java
import org.springframework.context.ApplicationEventPublisher;
import com.zentry.sigea.module_notificaciones.events.domain.PagoCompletadoEvent;
import java.time.LocalDateTime;

@Service
public class PagoService {
    
    private final CrearPagoUseCase crearPagoUseCase;
    private final ApplicationEventPublisher eventPublisher;  // ← AGREGAR
    
    public PagoService(
        CrearPagoUseCase crearPagoUseCase,
        ApplicationEventPublisher eventPublisher  // ← AGREGAR
    ) {
        this.crearPagoUseCase = crearPagoUseCase;
        this.eventPublisher = eventPublisher;  // ← AGREGAR
    }
    
    public String procesarPago(PagoRequest request) {
        String pagoId = crearPagoUseCase.execute(request);
        
        // ← AGREGAR ESTO
        eventPublisher.publishEvent(new PagoCompletadoEvent(
            request.getUsuarioId(),
            request.getInscripcionId(),
            request.getMonto(),
            pagoId,
            LocalDateTime.now()
        ));
        
        return pagoId;
    }
}
```

---

## 🔍 Verificación de la Integración

Después de implementar los cambios, verifica que funcione:

1. **Ejecuta el módulo** (ej: procesar un pago)
2. **Consulta las notificaciones** del usuario:
   ```
   GET http://localhost:16001/api/v1/notificaciones/usuario/{usuarioId}
   ```
3. **Verifica** que aparezca una nueva notificación automática con:
   - Tipo correcto (PAGO, CERTIFICADO, COMUNICACION)
   - Mensaje descriptivo sin emojis
   - Canal: SISTEMA
   - Estado: PENDIENTE

---

## ⚠️ Problemas Comunes

### Error: "No se pudo inyectar ApplicationEventPublisher"
**Solución**: Asegúrate de que la clase esté anotada con `@Service` y que Spring la esté escaneando.

### Las notificaciones no se crean
**Solución**: 
1. Verifica que el evento se esté publicando correctamente (agrega un log)
2. Revisa que el `EventListener` correspondiente exista en `module_notificaciones/events/listeners/`
3. Asegúrate de que `@EnableAsync` esté configurado en la aplicación

### Los mensajes salen con números (ej: 50914)
**Solución**: No uses emojis en los mensajes de los EventListeners. Ya están corregidos en el código actual.

---

## 📚 Referencias

- **Eventos de dominio**: `module_notificaciones/events/domain/`
- **Event Listeners**: `module_notificaciones/events/listeners/`
- **Documentación adicional**: 
  - `docs/SISTEMA_NOTIFICACIONES_AUTOMATICAS.md`
  - `docs/INTEGRACION_EVENTOS_NOTIFICACIONES.md`

---

## ✅ Checklist de Implementación

Para cada módulo pendiente:

- [ ] Agregar import de `ApplicationEventPublisher`
- [ ] Agregar import del Event correspondiente (PagoCompletadoEvent, etc.)
- [ ] Agregar import de `java.time.LocalDateTime`
- [ ] Declarar `ApplicationEventPublisher` como dependencia privada
- [ ] Agregarlo al constructor del Service
- [ ] Asignarlo en el constructor
- [ ] Publicar el evento después de la operación exitosa
- [ ] Probar que se genere la notificación automática
- [ ] Verificar que el mensaje salga correcto (sin números ni emojis)

---

**Fecha de creación**: 14 de noviembre de 2025  
**Autor**: Sistema de Notificaciones Automáticas  
**Versión**: 1.0
