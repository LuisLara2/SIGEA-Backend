# 📬 API de Notificaciones - Guía de Pruebas en Postman

## ✅ Estado del Módulo: **LISTO PARA PROBAR**

**Base URL:** `http://localhost:8080/api/v1/notificaciones`

---

## 🔧 Endpoints Disponibles

### 1. **Health Check** - Verificar que el servicio está activo
```http
GET http://localhost:8080/api/v1/notificaciones/health
```

**Respuesta Esperada:**
```
200 OK
"Notificaciones API is running"
```

---

### 2. **Crear Notificación**
```http
POST http://localhost:8080/api/v1/notificaciones/create
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "usuarioId": "uuid-del-usuario",
  "actividadId": "uuid-de-la-actividad",
  "tipoNotificacionId": "uuid-del-tipo",
  "mensaje": "Te has inscrito exitosamente a la actividad de Python",
  "estadoNotificacionId": "uuid-del-estado"
}
```

**Respuestas:**
- `201 Created` - Notificación creada exitosamente
- `400 Bad Request` - Datos inválidos
- `500 Internal Server Error` - Error del servidor

---

### 3. **Obtener Notificación por ID**
```http
GET http://localhost:8080/api/v1/notificaciones/{id}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/v1/notificaciones/123e4567-e89b-12d3-a456-426614174000
```

**Respuesta 200 OK:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "usuarioId": "uuid-usuario",
  "actividadId": "uuid-actividad",
  "tipoNotificacionCodigo": "INSCRIPCION",
  "tipoNotificacionEtiqueta": "Inscripción",
  "mensaje": "Te has inscrito exitosamente",
  "fechaEnvio": "2025-11-10T12:00:00",
  "estadoNotificacionCodigo": "PENDIENTE",
  "estadoNotificacionEtiqueta": "Pendiente",
  "createdAt": "2025-11-10T12:00:00",
  "updatedAt": "2025-11-10T12:00:00",
  "leida": false,
  "pendiente": true,
  "enviada": false
}
```

**Respuestas:**
- `200 OK` - Notificación encontrada
- `404 Not Found` - Notificación no existe

---

### 4. **Listar Todas las Notificaciones**
```http
GET http://localhost:8080/api/v1/notificaciones
```

**Respuesta 200 OK:** Array de notificaciones

---

### 5. **Listar Notificaciones por Usuario**
```http
GET http://localhost:8080/api/v1/notificaciones/usuario/{usuarioId}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/v1/notificaciones/usuario/123e4567-e89b-12d3-a456-426614174000
```

**Respuesta 200 OK:** Array de notificaciones del usuario

---

### 6. **Listar Notificaciones por Actividad**
```http
GET http://localhost:8080/api/v1/notificaciones/actividad/{actividadId}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/v1/notificaciones/actividad/123e4567-e89b-12d3-a456-426614174000
```

**Respuesta 200 OK:** Array de notificaciones de la actividad

---

### 7. **Actualizar Notificación (Marcar como Leída)**
```http
PUT http://localhost:8080/api/v1/notificaciones/{id}
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "mensaje": "Mensaje actualizado (opcional)",
  "estadoNotificacionId": "uuid-del-nuevo-estado"
}
```

**Respuesta 200 OK:** Notificación actualizada

---

### 8. **Eliminar Notificación**
```http
DELETE http://localhost:8080/api/v1/notificaciones/{id}
```

**Respuestas:**
- `204 No Content` - Eliminada exitosamente
- `404 Not Found` - Notificación no existe

---

## 📝 Datos de Prueba Necesarios

### 1. **Estados de Notificación** (tabla: `estado_notificacion`)
Necesitas insertar estos registros primero:

```sql
INSERT INTO estado_notificacion (id_estado_notificacion, codigo, etiqueta) 
VALUES 
  (gen_random_uuid(), 'PENDIENTE', 'Pendiente'),
  (gen_random_uuid(), 'LEIDA', 'Leída'),
  (gen_random_uuid(), 'ENVIADA', 'Enviada');
```

### 2. **Tipos de Notificación** (tabla: `tipo_notificacion`)
```sql
INSERT INTO tipo_notificacion (id_tipo_notificacion, codigo, etiqueta) 
VALUES 
  (gen_random_uuid(), 'INSCRIPCION', 'Inscripción'),
  (gen_random_uuid(), 'ASISTENCIA', 'Asistencia'),
  (gen_random_uuid(), 'CERTIFICADO', 'Certificado'),
  (gen_random_uuid(), 'ACTIVIDAD', 'Actividad'),
  (gen_random_uuid(), 'SISTEMA', 'Sistema');
```

### 3. **Usuarios y Actividades**
Necesitas tener usuarios y actividades creados previamente para referenciarlos.

---

## 🧪 Orden Sugerido de Pruebas

1. ✅ **Health Check** - Verificar que el servicio esté activo
2. ✅ **Crear Estados** - Insertar estados en la BD
3. ✅ **Crear Tipos** - Insertar tipos en la BD
4. ✅ **Crear Notificación** - POST para crear
5. ✅ **Obtener por ID** - GET con el ID devuelto
6. ✅ **Listar Todas** - GET sin parámetros
7. ✅ **Listar por Usuario** - GET con usuarioId
8. ✅ **Actualizar** - PUT para marcar como leída
9. ✅ **Eliminar** - DELETE

---

## 🎯 Validaciones Implementadas

- ✅ Usuario obligatorio
- ✅ Tipo de notificación obligatorio
- ✅ Mensaje obligatorio (no vacío)
- ✅ Estado de notificación obligatorio
- ✅ Actividad opcional
- ✅ Validación de existencia en actualizaciones
- ✅ Validación de existencia en eliminaciones

---

## 🚀 Para Iniciar el Servidor

### Opción 1: Ejecutar SOLO el módulo de Notificaciones (RECOMENDADO)

**Desde terminal:**
```bash
cd /c/Users/NILVER/Desktop/Proyectos/Arquitectura/SIGEA
./mvnw spring-boot:run -Dstart-class=com.zentry.sigea.module_notificaciones.NotificacionesApplication
```

**Desde PowerShell:**
```powershell
cd C:\Users\NILVER\Desktop\Proyectos\Arquitectura\SIGEA
.\mvnw spring-boot:run -D"start-class=com.zentry.sigea.module_notificaciones.NotificacionesApplication"
```

**Desde IDE (IntelliJ/Eclipse):**
- Abrir el archivo: `src/main/java/com/zentry/sigea/module_notificaciones/NotificacionesApplication.java`
- Click derecho → Run 'NotificacionesApplication'

### Opción 2: Ejecutar toda la aplicación

```bash
cd /c/Users/NILVER/Desktop/Proyectos/Arquitectura/SIGEA
./mvnw spring-boot:run
```

### Opción 3: Ejecutar el JAR

```bash
java -jar target/sigea-0.0.1-SNAPSHOT.jar
```

---

## 📊 Estructura de Respuesta

Todas las respuestas incluyen:
- `id`: UUID de la notificación
- `usuarioId`: UUID del usuario
- `actividadId`: UUID de la actividad (opcional)
- `tipoNotificacionCodigo` y `tipoNotificacionEtiqueta`
- `estadoNotificacionCodigo` y `estadoNotificacionEtiqueta`
- `mensaje`: Contenido de la notificación
- `fechaEnvio`: Timestamp de envío
- `createdAt` y `updatedAt`: Auditoría
- `leida`, `pendiente`, `enviada`: Booleanos calculados

---

## ✅ Módulo Completamente Funcional

El módulo de notificaciones está **100% implementado** con:
- ✅ Arquitectura hexagonal
- ✅ Clean Architecture
- ✅ Principios SOLID
- ✅ CRUD completo
- ✅ Validaciones de negocio
- ✅ Manejo de errores
- ✅ Preparado para integraciones futuras (Email, WhatsApp)
