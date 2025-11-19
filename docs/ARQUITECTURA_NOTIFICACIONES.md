# Arquitectura del Módulo de Notificaciones Multi-Canal

## 🎯 Vista General

```
┌─────────────────────────────────────────────────────────────────────┐
│                     CAPA DE PRESENTACIÓN                             │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │           NotificacionController (API REST)                   │  │
│  │  POST   /api/notificaciones                                   │  │
│  │  GET    /api/notificaciones/{id}                              │  │
│  │  GET    /api/notificaciones                                   │  │
│  │  PUT    /api/notificaciones/{id}                              │  │
│  │  DELETE /api/notificaciones/{id}                              │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────┐
│                     CAPA DE SERVICIOS                                │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │              NotificacionService (Orquestador)                │  │
│  │  • Coordina casos de uso                                      │  │
│  │  • Decide canal de envío (SISTEMA/CORREO/WHATSAPP)          │  │
│  │  • Maneja errores y actualiza estados                        │  │
│  └──────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────┐
│                     CAPA DE CASOS DE USO                             │
│  ┌────────────────────┐  ┌────────────────────┐  ┌──────────────┐  │
│  │ CrearNotificacion  │  │ActualizarEstado    │  │ Listar       │  │
│  │ UseCase            │  │NotificacionUseCase │  │ Notificaciones│ │
│  └────────────────────┘  └────────────────────┘  └──────────────┘  │
│  ┌────────────────────┐  ┌────────────────────┐                    │
│  │ ObtenerPorId       │  │ Eliminar           │                    │
│  │ UseCase            │  │ NotificacionUseCase│                    │
│  └────────────────────┘  └────────────────────┘                    │
└─────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────┐
│                CAPA DE DOMINIO (PUERTOS - INTERFACES)               │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐ │
│  │ IEmailService    │  │ IWhatsAppService │  │ IUsuarioGateway  │ │
│  │ • enviar()       │  │ • enviar()       │  │ • obtenerCorreo()│ │
│  └──────────────────┘  └──────────────────┘  │ • obtenerTel()   │ │
│                                               │ • obtenerNombre()│ │
│  ┌──────────────────────────────────────┐    └──────────────────┘ │
│  │ INotificacionRepository              │                          │
│  │ • save()                             │                          │
│  │ • findById()                         │                          │
│  │ • findAll()                          │                          │
│  └──────────────────────────────────────┘                          │
└─────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────┐
│              CAPA DE INFRAESTRUCTURA (ADAPTADORES)                  │
│  ┌────────────────────────────────────────────────────────────────┐│
│  │                    ADAPTADORES EXTERNOS                         ││
│  │  ┌─────────────────┐  ┌──────────────────┐  ┌────────────────┐││
│  │  │EmailServiceImpl │  │WhatsAppServiceImpl│ │UsuarioGateway  │││
│  │  │(JavaMailSender) │  │(Twilio API)       │ │Adapter         │││
│  │  │                 │  │                   │ │                │││
│  │  │• Plantilla HTML │  │• Formato mensaje  │ │• JPA Usuario   │││
│  │  │• SMTP Config    │  │• Twilio SDK       │ │  Repository    │││
│  │  └─────────────────┘  └──────────────────┘  └────────────────┘││
│  └────────────────────────────────────────────────────────────────┘│
│  ┌────────────────────────────────────────────────────────────────┐│
│  │                  ADAPTADORES DE PERSISTENCIA                    ││
│  │  ┌────────────────────────────────────────────────────────────┐││
│  │  │         NotificacionRepositoryImpl (JPA)                    │││
│  │  │  ┌─────────────────┐  ┌───────────────────────────────┐   │││
│  │  │  │NotificacionJpa  │  │  NotificacionEntity (DB)       │   │││
│  │  │  │Repository       │  │  • id (UUID)                   │   │││
│  │  │  │(Spring Data)    │  │  • usuario_id                  │   │││
│  │  │  └─────────────────┘  │  • titulo                      │   │││
│  │  │                       │  • mensaje                     │   │││
│  │  │                       │  • canal (ENUM)                │   │││
│  │  │                       │  • estado_id                   │   │││
│  │  │                       │  • tipo_notificacion_id        │   │││
│  │  │                       └───────────────────────────────┘   │││
│  │  └────────────────────────────────────────────────────────────┘││
│  └────────────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────────────┘
                                    ↓
┌─────────────────────────────────────────────────────────────────────┐
│                    SERVICIOS EXTERNOS                                │
│  ┌──────────────┐    ┌─────────────┐    ┌─────────────────────┐   │
│  │   Gmail/     │    │   Twilio    │    │   PostgreSQL DB     │   │
│  │   SMTP       │    │  WhatsApp   │    │   (Persistencia)    │   │
│  │   Server     │    │     API     │    │                     │   │
│  └──────────────┘    └─────────────┘    └─────────────────────┘   │
└─────────────────────────────────────────────────────────────────────┘
```

## 🔄 Flujo de Creación de Notificación

```
  Usuario
    │
    │ POST /api/notificaciones
    │ { usuarioId, titulo, mensaje, canal: "WHATSAPP" }
    ↓
┌────────────────────────────────┐
│  NotificacionController        │
│  • Valida Request              │
│  • Llama a Service             │
└────────────────────────────────┘
    │
    ↓
┌────────────────────────────────┐
│  NotificacionService           │ ← Orquestador Principal
│  1. Llama a CrearUseCase       │
│  2. Guarda en BD               │
│  3. Evalúa canal               │
│  4. Orquesta envío             │
└────────────────────────────────┘
    │
    ├─────────────────────────────────┬──────────────────────────────┐
    ↓                                 ↓                              ↓
┌─────────────────┐       ┌───────────────────┐        ┌──────────────────┐
│ canal=SISTEMA   │       │  canal=CORREO     │        │ canal=WHATSAPP   │
│ • Solo BD       │       │  1. Obtener correo│        │ 1. Obtener tel   │
│ • Fin           │       │     via Gateway   │        │    via Gateway   │
└─────────────────┘       │  2. EmailService  │        │ 2. WhatsAppService│
                          │  3. Enviar email  │        │ 3. Enviar msg    │
                          └───────────────────┘        └──────────────────┘
                                    │                            │
                                    ↓                            ↓
                          ┌─────────────────┐        ┌─────────────────────┐
                          │ ¿Éxito?         │        │ ¿Éxito?             │
                          └─────────────────┘        └─────────────────────┘
                              │       │                    │         │
                           Sí │       │ No              Sí │         │ No
                              ↓       ↓                    ↓         ↓
                         ┌────────┐ ┌──────────────┐ ┌────────┐ ┌──────────────┐
                         │ Estado │ │ActualizarEstado│ Estado │ │ActualizarEstado│
                         │ENVIADA │ │UseCase         │ENVIADA │ │UseCase         │
                         └────────┘ │Estado=FALLIDA  └────────┘ │Estado=FALLIDA  │
                                    └──────────────┘            └──────────────┘
                                          │                            │
                                          └────────────┬───────────────┘
                                                       ↓
                                          ┌──────────────────────────┐
                                          │ Respuesta al Usuario     │
                                          │ • Éxito: "Enviada"       │
                                          │ • Error: "Fallida"       │
                                          └──────────────────────────┘
```

## 🔌 Patrón Hexagonal - Puertos y Adaptadores

### 🎯 PUERTOS (Interfaces - Dominio)

**Puertos de Salida** (conectan dominio con infraestructura):
```
INotificacionRepository      → Para persistencia
IEmailService                → Para envío de emails
IWhatsAppService             → Para envío de WhatsApp
IUsuarioGateway              → Para acceso a datos de usuarios
```

### 🔧 ADAPTADORES (Implementaciones - Infraestructura)

**Adaptadores de Salida** (implementan los puertos):
```
NotificacionRepositoryImpl   → Implementa INotificacionRepository usando JPA
EmailServiceImpl             → Implementa IEmailService usando JavaMailSender
WhatsAppServiceImpl          → Implementa IWhatsAppService usando Twilio SDK
UsuarioGatewayAdapter        → Implementa IUsuarioGateway usando UsuarioJPARepository
```

### ✨ Ventajas del Patrón

1. **Desacoplamiento**: El dominio no conoce los detalles de infraestructura
2. **Testabilidad**: Fácil mockear interfaces para testing
3. **Flexibilidad**: Cambiar Twilio por otra API sin tocar dominio
4. **Mantenibilidad**: Cambios aislados por capas
5. **Escalabilidad**: Agregar nuevos canales (SMS, Push) sin modificar core

## 📦 Estructura de Archivos

```
module_notificaciones/
│
├── 📂 core/                                    # DOMINIO
│   ├── 📂 entities/
│   │   ├── NotificacionDomainEntity.java       # Entidad de dominio
│   │   ├── EstadoNotificacionEntity.java
│   │   ├── TipoNotificacionEntity.java
│   │   └── CanalNotificacion.java              # Enum (SISTEMA/CORREO/WHATSAPP)
│   │
│   └── 📂 ports/                               # PUERTOS (interfaces)
│       ├── INotificacionRepository.java
│       ├── IEmailService.java                  # Puerto para email
│       ├── IWhatsAppService.java               # Puerto para WhatsApp
│       └── IUsuarioGateway.java                # Puerto para acceso a usuarios
│
├── 📂 infrastructure/                          # INFRAESTRUCTURA
│   ├── 📂 adapters/                            # ADAPTADORES
│   │   └── UsuarioGatewayAdapter.java          # Implementa IUsuarioGateway
│   │
│   ├── 📂 external/                            # SERVICIOS EXTERNOS
│   │   ├── EmailServiceImpl.java               # Implementa IEmailService
│   │   └── WhatsAppServiceImpl.java            # Implementa IWhatsAppService
│   │
│   ├── 📂 database/
│   │   ├── 📂 entities/
│   │   │   ├── NotificacionEntity.java         # Entidad JPA
│   │   │   ├── EstadoNotificacionEntity.java
│   │   │   └── TipoNotificacionEntity.java
│   │   │
│   │   └── 📂 mappers/
│   │       └── NotificacionMapper.java         # Mapea Domain ↔ Entity
│   │
│   └── 📂 repositories/
│       ├── NotificacionRepositoryImpl.java     # Implementa INotificacionRepository
│       └── 📂 spring/
│           ├── NotificacionJpaRepository.java
│           ├── EstadoNotificacionJpaRepository.java
│           └── TipoNotificacionJpaRepository.java
│
├── 📂 services/                                # APLICACIÓN
│   ├── NotificacionService.java                # ORQUESTADOR PRINCIPAL
│   │
│   └── 📂 usecases/                            # CASOS DE USO
│       ├── CrearNotificacionUseCase.java
│       ├── ActualizarEstadoNotificacionUseCase.java
│       ├── ObtenerNotificacionPorIdUseCase.java
│       ├── ListarNotificacionesUseCase.java
│       ├── ActualizarNotificacionUseCase.java
│       └── EliminarNotificacionUseCase.java
│
└── 📂 presentation/                            # PRESENTACIÓN
    ├── 📂 api/
    │   └── NotificacionController.java         # REST Controller
    │
    └── 📂 models/
        ├── CrearNotificacionRequest.java       # DTO Request
        ├── ActualizarNotificacionRequest.java
        └── NotificacionDTO.java                # DTO Response
```

## 🎨 Diagrama de Componentes

```
┌───────────────────────────────────────────────────────────────────┐
│                      MÓDULO DE NOTIFICACIONES                      │
│                                                                    │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │                    PRESENTATION LAYER                     │    │
│  │  • NotificacionController (REST API)                      │    │
│  │  • DTOs: Request/Response                                 │    │
│  └──────────────────────────────────────────────────────────┘    │
│                              ↓↑                                    │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │                    APPLICATION LAYER                      │    │
│  │  • NotificacionService (Orchestrator)                     │    │
│  │  • Use Cases (Business Logic)                             │    │
│  └──────────────────────────────────────────────────────────┘    │
│                              ↓↑                                    │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │                      DOMAIN LAYER                         │    │
│  │  • Domain Entities                                        │    │
│  │  • Ports (Interfaces):                                    │    │
│  │    - INotificacionRepository                              │    │
│  │    - IEmailService                                        │    │
│  │    - IWhatsAppService                                     │    │
│  │    - IUsuarioGateway                                      │    │
│  └──────────────────────────────────────────────────────────┘    │
│                              ↓↑                                    │
│  ┌──────────────────────────────────────────────────────────┐    │
│  │                  INFRASTRUCTURE LAYER                     │    │
│  │  • Adapters (Implementations):                            │    │
│  │    - NotificacionRepositoryImpl (JPA)                     │    │
│  │    - EmailServiceImpl (JavaMail)                          │    │
│  │    - WhatsAppServiceImpl (Twilio)                         │    │
│  │    - UsuarioGatewayAdapter (JPA)                          │    │
│  │  • Database Entities (JPA)                                │    │
│  │  • Mappers (Domain ↔ Entity)                              │    │
│  └──────────────────────────────────────────────────────────┘    │
│                                                                    │
└───────────────────────────────────────────────────────────────────┘
              ↓                    ↓                    ↓
    ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐
    │   PostgreSQL    │  │   SMTP Server   │  │   Twilio API    │
    │   Database      │  │   (Email)       │  │   (WhatsApp)    │
    └─────────────────┘  └─────────────────┘  └─────────────────┘
```

## 🔐 Principios Aplicados

### ✅ SOLID

- **S** - Single Responsibility: Cada clase tiene una responsabilidad única
- **O** - Open/Closed: Abierto para extensión (nuevos canales), cerrado para modificación
- **L** - Liskov Substitution: Implementaciones intercambiables de interfaces
- **I** - Interface Segregation: Interfaces pequeñas y específicas
- **D** - Dependency Inversion: Dependencia de abstracciones (puertos), no de implementaciones

### 🏗️ Clean Architecture

- **Independencia de Frameworks**: Dominio no depende de Spring/JPA
- **Testeable**: Fácil mockear interfaces
- **Independencia de UI**: Controller desacoplado de lógica
- **Independencia de BD**: Repositorio implementa puerto del dominio
- **Independencia de Servicios Externos**: Twilio/JavaMail intercambiables

### 📦 DDD (Domain-Driven Design)

- **Entidades de Dominio**: NotificacionDomainEntity con lógica de negocio
- **Casos de Uso**: Representan acciones del negocio
- **Puertos**: Abstracciones del dominio
- **Adaptadores**: Implementaciones técnicas fuera del dominio

---

**Última actualización**: 2025  
**Versión**: 1.0.0
