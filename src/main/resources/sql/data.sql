-- 1) Limpiar e insertar roles
-- Usamos DELETE primero para evitar duplicados

DELETE FROM usuario_rol ur
USING usuario u, rol r
WHERE ur.usuario_id = u.id_usuario
  AND ur.rol_id = r.id_rol
  AND (
        (u.correo = 'administrador@sigea.unas.edu.pe' AND r.nombre_rol = 'ADMINISTRADOR') OR
        (u.correo = 'organizador@sigea.unas.edu.pe' AND r.nombre_rol = 'ORGANIZADOR') OR
        (u.correo = 'participante@sigea.unas.edu.pe' AND r.nombre_rol = 'PARTICIPANTE')
      );

DELETE FROM usuario WHERE correo IN (
    'administrador@sigea.unas.edu.pe',
    'organizador@sigea.unas.edu.pe',
    'participante@sigea.unas.edu.pe'
);

DELETE FROM rol WHERE nombre_rol IN ('ADMINISTRADOR', 'ORGANIZADOR', 'PARTICIPANTE');

-- Insertar roles de manera segura
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

-- 2) Limpiar e insertar usuarios

-- Inserción de usuarios usando SELECT de roles
INSERT INTO usuario (
    nombres, 
    apellidos , 
    correo, 
    password_hash, 
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Administrador', 
    'Ordoñez' , 
    'administrador@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    now(), 
    now(),
    '900800701' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

INSERT INTO usuario (
    nombres, 
    apellidos , 
    correo, 
    password_hash, 
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Organizador', 
    'Castro' , 
    'organizador@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    now(), 
    now(),
    '976879800' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

INSERT INTO usuario (
    nombres, 
    apellidos , 
    correo, 
    password_hash, 
    created_at , 
    updated_at , 
    telefono , 
    extension_telefonica
)
VALUES (
    'Participante', 
    'Escaly' , 
    'participante@sigea.unas.edu.pe', 
    crypt('12345678', gen_salt('bf', 10)), 
    now(), 
    now(),
    '987600799' , 
    '+51'
)
ON CONFLICT (correo) DO NOTHING;

-- 5) Insertar relaciones usuario-rol (comentado para evitar duplicados)
INSERT INTO usuario_rol (usuario_id, rol_id, asignado_en)
SELECT 
    u.id_usuario,
    r.id_rol, 
    NOW()
FROM 
    usuario u
JOIN rol r ON (
    (u.correo = 'administrador@sigea.unas.edu.pe' AND r.nombre_rol = 'ADMINISTRADOR') OR
    (u.correo = 'organizador@sigea.unas.edu.pe' AND r.nombre_rol = 'ORGANIZADOR') OR
    (u.correo = 'participante@sigea.unas.edu.pe' AND r.nombre_rol = 'PARTICIPANTE')
);


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

-- 6) Insertar estados de actividad
INSERT INTO estado_actividad (codigo, etiqueta)
VALUES 
    ('BORRADOR', 'Borrador'),
    ('PUBLICADO', 'Publicado'),
    ('EN_CURSO', 'En Curso'),
    ('FINALIZADO', 'Finalizado'),
    ('CANCELADO', 'Cancelado'),
    ('SUSPENDIDO', 'Suspendido')
ON CONFLICT (codigo) DO NOTHING;

-- 7) Insertar actividad de ejemplo (solo si el organizador existe y no tiene duplicados)
INSERT INTO actividad (
    titulo, descripcion, fecha_inicio, fecha_fin, hora_inicio, hora_fin,
    estado_actividad_id, id_usuario_rol, tipo_actividad_id, lugar, 
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
    ur.id_usuario_rol,
    ta.id_tipo_actividad,
    'Aula Virtual 101',
    'Co-Organizador Ejemplo',
    'Sponsor Ejemplo',
    'https://example.com/banner.jpg',
    NOW(),
    NOW()
FROM estado_actividad ea
CROSS JOIN usuario_rol ur
CROSS JOIN tipo_actividad ta
WHERE ea.codigo = 'BORRADOR'
  AND ta.nombre_actividad = 'CURSO'
  AND ur.usuario_id = (SELECT id_usuario FROM usuario WHERE correo = 'organizador@sigea.unas.edu.pe')
  AND ur.rol_id = (SELECT id_rol FROM rol WHERE nombre_rol = 'ORGANIZADOR')
  AND NOT EXISTS (
      SELECT 1 FROM actividad 
      WHERE titulo = 'Curso de Introducción a la Programación'
        AND id_usuario_rol = ur.id_usuario_rol
  );


-- Verificar usuarios creados
SELECT u.id_usuario, u.correo, u.nombres, u.apellidos 
FROM usuario u 
WHERE u.correo IN ('administrador@sigea.unas.edu.pe', 'organizador@sigea.unas.edu.pe', 'participante@sigea.unas.edu.pe');

-- Verificar relaciones usuario-rol sin duplicados
SELECT ur.id_usuario_rol, u.correo, r.nombre_rol, COUNT(*) as cantidad
FROM usuario_rol ur
JOIN usuario u ON ur.usuario_id = u.id_usuario
JOIN rol r ON ur.rol_id = r.id_rol
GROUP BY ur.id_usuario_rol, u.correo, r.nombre_rol
HAVING COUNT(*) > 1;

-- Verificar actividades creadas
SELECT a.id_actividad, a.titulo, u.correo, r.nombre_rol
FROM actividad a
JOIN usuario_rol ur ON a.id_usuario_rol = ur.id_usuario_rol
JOIN usuario u ON ur.usuario_id = u.id_usuario
JOIN rol r ON ur.rol_id = r.id_rol;