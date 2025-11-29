-- ============================================
-- SCRIPT DE DATOS INICIALES - SIN DELETE
-- ============================================
-- Este script es SEGURO: solo inserta datos nuevos
-- Si ya existen, NO hace nada (ON CONFLICT DO NOTHING)

-- 1) Insertar roles (sin eliminar existentes)
INSERT INTO rol (nombre_rol, descripcion, created_at, updated_at) 
VALUES 
    ('ADMINISTRADOR', 
    'Usuario con mayor nivel de acceso dentro del sistema. Su función principal es gestionar la plataforma en su totalidad, asegurando su correcto funcionamiento y supervisando las actividades registradas.', 
    NOW(), NOW()),

    ('ORGANIZADOR', 
    'Responsable de la planificación, ejecución y seguimiento de las actividades académicas o institucionales (como cursos, talleres, conferencias o diplomados).', 
    NOW(), NOW()),

    ('PARTICIPANTE', 
    'Es el usuario que se inscribe y asiste a las actividades ofrecidas en el sistema. Puede ser alumno, egresado, docente, personal administrativo o público externo.', 
    NOW(), NOW())
ON CONFLICT (nombre_rol) DO NOTHING;

-- 2) Insertar usuarios (sin eliminar existentes)
INSERT INTO usuario (
    nombres, 
    apellidos, 
    correo, 
    password_hash, 
    dni,
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Administrador', 
    'Ordoñez', 
    'administrador@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    '80787898' , 
    now(), 
    now(),
    '900800701' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

INSERT INTO usuario (
    nombres, 
    apellidos, 
    correo, 
    password_hash, 
    dni , 
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Organizador', 
    'Castro', 
    'organizador@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    '67987989' , 
    now(), 
    now(),
    '976879800' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

INSERT INTO usuario (
    nombres, 
    apellidos, 
    correo, 
    password_hash, 
    dni , 
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Participante', 
    'Escaly', 
    'participante@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    '70897889' , 
    now(), 
    now(),
    '987600799' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

-- 3) Insertar relaciones usuario-rol (verifica duplicados antes de insertar)
INSERT INTO usuario_rol (usuario_id, rol_id, asignado_en)
SELECT 
    u.id_usuario,
    r.id_rol, 
    NOW()
FROM usuario u
JOIN rol r ON (
    (u.correo = 'administrador@sigea.unas.edu.pe' AND r.nombre_rol = 'ADMINISTRADOR') OR
    (u.correo = 'organizador@sigea.unas.edu.pe' AND r.nombre_rol = 'ORGANIZADOR') OR
    (u.correo = 'participante@sigea.unas.edu.pe' AND r.nombre_rol = 'PARTICIPANTE')
)
WHERE NOT EXISTS (
    SELECT 1 FROM usuario_rol ur
    WHERE ur.usuario_id = u.id_usuario
    AND ur.rol_id = r.id_rol
)
ON CONFLICT DO NOTHING;

-- 4) Insertar tipos de actividad
INSERT INTO tipo_actividad (nombre_actividad, descripcion, created_at, updated_at)
VALUES 
    ('CURSO', 'Programa de formación estructurado con sesiones programadas y objetivos de aprendizaje definidos.', NOW(), NOW()),
    ('TALLER', 'Actividad práctica de corta duración enfocada en desarrollar habilidades específicas.', NOW(), NOW()),
    ('CONFERENCIA', 'Evento donde expertos presentan temas de interés académico o profesional.', NOW(), NOW()),
    ('SEMINARIO', 'Reunión especializada para el estudio y discusión de temas específicos.', NOW(), NOW()),
    ('DIPLOMADO', 'Programa de formación continua de larga duración con certificación.', NOW(), NOW()),
    ('CONGRESO', 'Evento académico de gran escala con múltiples ponencias y actividades.', NOW(), NOW()),
    ('CHARLA', 'Presentación informal sobre un tema específico de corta duración.', NOW(), NOW()),
    ('WEBINAR', 'Seminario o conferencia realizada en línea.', NOW(), NOW())
ON CONFLICT (nombre_actividad) DO NOTHING;

-- 5) Insertar estados de actividad
INSERT INTO estado_actividad (codigo, etiqueta)
VALUES 
    ('BORRADOR', 'Borrador'),
    ('PUBLICADO', 'Publicado'),
    ('EN_CURSO', 'En Curso'),
    ('FINALIZADO', 'Finalizado'),
    ('CANCELADO', 'Cancelado'),
    ('SUSPENDIDO', 'Suspendido')
ON CONFLICT (codigo) DO NOTHING;

-- 6) Insertar actividad de ejemplo (verifica duplicados antes de insertar)
INSERT INTO actividad (
    titulo, descripcion, fecha_inicio, fecha_fin, hora_inicio, hora_fin,
    estado_actividad_id, id_usuario, tipo_actividad_id, lugar, 
    co_organizador, sponsor, banner_url, created_at, updated_at
)
SELECT 
    'Curso de Introducción a la Programación',
    'Curso básico para aprender los fundamentos de la programación con ejemplos prácticos.',
    CURRENT_DATE + INTERVAL '7 days',
    CURRENT_DATE + INTERVAL '30 days',
    '09:00:00'::TIME,
    '12:00:00'::TIME,
    ea.id_estado_actividad,
    u.id_usuario,
    ta.id_tipo_actividad,
    'Aula Virtual 101',
    'Co-Organizador Ejemplo',
    'Sponsor Ejemplo',
    'https://example.com/banner.jpg',
    NOW(),
    NOW()
FROM estado_actividad ea
CROSS JOIN usuario u
CROSS JOIN tipo_actividad ta
WHERE ea.codigo = 'BORRADOR'
  AND ta.nombre_actividad = 'CURSO'
  AND u.correo = 'organizador@sigea.unas.edu.pe'
  AND NOT EXISTS (
      SELECT 1 FROM actividad a
      WHERE a.titulo = 'Curso de Introducción a la Programación'
        AND a.id_usuario = u.id_usuario
  )
ON CONFLICT DO NOTHING;