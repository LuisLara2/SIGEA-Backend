-- ============================================
-- Script para insertar Estados y Tipos de Notificación
-- Sistema SIGEA - Módulo de Notificaciones
-- ============================================

-- ============================================
-- 1. INSERTAR ESTADOS DE NOTIFICACIÓN
-- ============================================

-- Estado PENDIENTE (obligatorio para el sistema)
INSERT INTO estado_notificacion (codigo, etiqueta) 
VALUES ('PENDIENTE', 'Pendiente de lectura')
ON CONFLICT (codigo) DO NOTHING;

-- Estado LEIDA
INSERT INTO estado_notificacion (codigo, etiqueta) 
VALUES ('LEIDA', 'Leída por el usuario')
ON CONFLICT (codigo) DO NOTHING;

-- Estado ARCHIVADA (opcional)
INSERT INTO estado_notificacion (codigo, etiqueta) 
VALUES ('ARCHIVADA', 'Archivada')
ON CONFLICT (codigo) DO NOTHING;

-- ============================================
-- 2. INSERTAR TIPOS DE NOTIFICACIÓN
-- ============================================

-- Tipo COMUNICACION (para actividades nuevas)
INSERT INTO tipo_notificacion (codigo, etiqueta) 
VALUES ('COMUNICACION', 'Difusión de eventos o anuncios del sistema')
ON CONFLICT (codigo) DO NOTHING;

-- Tipo INSCRIPCION (para confirmación de inscripciones)
INSERT INTO tipo_notificacion (codigo, etiqueta) 
VALUES ('INSCRIPCION', 'Confirmación de inscripción')
ON CONFLICT (codigo) DO NOTHING;

-- Tipo SESION (para nuevas sesiones)
INSERT INTO tipo_notificacion (codigo, etiqueta) 
VALUES ('SESION', 'Notificación de sesión')
ON CONFLICT (codigo) DO NOTHING;

-- Tipo PAGO (para confirmación de pagos)
INSERT INTO tipo_notificacion (codigo, etiqueta) 
VALUES ('PAGO', 'Confirmación de pago')
ON CONFLICT (codigo) DO NOTHING;

-- Tipo CERTIFICADO (para certificados generados)
INSERT INTO tipo_notificacion (codigo, etiqueta) 
VALUES ('CERTIFICADO', 'Certificado generado')
ON CONFLICT (codigo) DO NOTHING;

-- ============================================
-- 3. VERIFICAR INSERCIONES
-- ============================================

-- Ver todos los estados
SELECT * FROM estado_notificacion ORDER BY codigo;

-- Ver todos los tipos
SELECT * FROM tipo_notificacion ORDER BY codigo;

-- ============================================
-- FIN DEL SCRIPT
-- ============================================
