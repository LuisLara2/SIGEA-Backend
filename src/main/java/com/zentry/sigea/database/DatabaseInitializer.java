package com.zentry.sigea.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;


@Component
public class DatabaseInitializer implements CommandLineRunner {
    
    private final JdbcTemplate jdbcTemplate;

    private final Dotenv dotenv = Dotenv.load();

    // Cargar las variables de entorno usando dotenv
    private final String databaseName = dotenv.get("DB_NAME");
    private final String databaseUsername = dotenv.get("DB_USERNAME");
    private final String databasePassword = dotenv.get("DB_PASSWORD");

    private final String databaseAdminUsername = dotenv.get("DB_ADMIN_USERNAME");
    private final String databaseAdminUserPassword = dotenv.get("DB_ADMIN_USER_PASSWORD");
    private final String databaseHost = dotenv.get("DB_HOST");
    private final String databasePort = dotenv.get("DB_PORT");

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Crear el archivo .pgpass
            createPgpassFile();

            // Crear la base de datos si no existe
            createDatabaseIfNotExists();

            // Crear el usuario si no existe
            createUserIfNotExists();

            // Asignar permisos al usuario
            assignPermissionsToUser();

            System.out.println("Base de datos y usuario creados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la base de datos o usuarios: " + e.getMessage());
        }
    }

    private void createDatabaseIfNotExists() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        String createDbCommand;

        // Comando para verificar la base de datos y crearla si no existe
        if (os.contains("win")) {
            // Windows: Usamos psql para ejecutar la consulta sin necesidad de grep
            createDbCommand = String.format(
                "psql -U %s -h %s -p %s -tc \"SELECT 1 FROM pg_database WHERE datname='%s';\" || psql -U %s -h %s -p %s -c \"CREATE DATABASE %s;\"",
                databaseAdminUsername, 
                databaseHost, 
                databasePort, 
                databaseName,
                databaseAdminUsername, 
                databaseHost, 
                databasePort, 
                databaseName
            );
        } else {
            // Linux / Mac: Usamos psql y bash sin grep
            createDbCommand = String.format(
                "psql -U %s -h %s -p %s -tc \"SELECT 1 FROM pg_database WHERE datname='%s';\" | grep -q 1 || psql -U %s -h %s -p %s -c \"CREATE DATABASE %s;\"",
                databaseAdminUsername, 
                databaseHost, 
                databasePort, 
                databaseName,
                databaseAdminUsername, 
                databaseHost, 
                databasePort, 
                databaseName
            );
        }

        executeShellCommand(createDbCommand);
    }

    private void createUserIfNotExists() {
        // Crear usuario si no existe
        String createUserQuery = String.format(
            "DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = '%s') THEN CREATE USER %s WITH ENCRYPTED PASSWORD '%s'; END IF; END $$;",
            databaseUsername, 
            databaseUsername, 
            databasePassword
        );
        jdbcTemplate.execute(createUserQuery);
    }

    private void assignPermissionsToUser() {
        // Asignar permisos básicos
        String grantPermissionsQuery = String.format(
            "GRANT CONNECT ON DATABASE %s TO %s;", 
            databaseName, 
            databaseUsername
        );
        jdbcTemplate.execute(grantPermissionsQuery);

        // Permisos de lectura (SELECT) en todas las tablas
        String grantSelectQuery = String.format(
            "GRANT SELECT ON ALL TABLES IN SCHEMA public TO %s;", 
            databaseUsername
        );
        jdbcTemplate.execute(grantSelectQuery);

        // Permisos de escritura (INSERT, UPDATE, DELETE) en todas las tablas
        String grantWriteQuery = String.format(
            "GRANT INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO %s;", 
            databaseUsername
        );
        jdbcTemplate.execute(grantWriteQuery);

        // Permisos de ejecución sobre todas las funciones (si es necesario)
        String grantExecuteQuery = String.format(
            "GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO %s;", 
            databaseUsername
        );
        jdbcTemplate.execute(grantExecuteQuery);

        // Permisos sobre secuencias
        String grantSequenceQuery = String.format(
            "GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO %s;", 
            databaseUsername
        );
        jdbcTemplate.execute(grantSequenceQuery);

        // Asegurar que futuros objetos también reciban estos permisos
        String grantDefaultPrivilegesQuery = String.format(
            "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO %s;", 
            databaseUsername
        );
        jdbcTemplate.execute(grantDefaultPrivilegesQuery);
    }

    private void executeShellCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();

        String os = System.getProperty("os.name").toLowerCase();

        // Verifica el sistema operativo para ajustar las rutas y configuraciones
        if (os.contains("win")) {
            // Para Windows
            String pgSqlPath = "C:\\Program Files\\PostgreSQL\\17\\bin";  // Ajusta la ruta según sea necesario
            processBuilder.environment().put("PATH", processBuilder.environment().get("PATH") + ";" + pgSqlPath);
            // Ejecutar el comando con cmd.exe, que es más adecuado en Windows
            processBuilder.command("cmd.exe", "/c", command);  // Usamos "cmd.exe /c" para ejecutar el comando
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Para Linux y MacOS
            String pgSqlPath = "/usr/local/pgsql/bin";  // Ajusta esta ruta si PostgreSQL está en otra ubicación
            processBuilder.environment().put("PATH", processBuilder.environment().get("PATH") + ":" + pgSqlPath);
            // Ejecutar el comando con bash, que es común en sistemas Linux y Mac
            processBuilder.command("bash", "-c", command);  // Usamos "bash -c" para ejecutar el comando
        } else {
            throw new UnsupportedOperationException("Sistema operativo no soportado");
        }
        
        Process process = processBuilder.start();
        
        // Leer salida de errores
        StringBuilder output = new StringBuilder();
        StringBuilder errorOutput = new StringBuilder();
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Comando fallido con código de salida: " + exitCode + "\n" + errorOutput.toString());
        }
        
        System.out.println("Salida del comando:\n" + output.toString());
    }

    private void createPgpassFile() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        // Ruta para el archivo .pgpass según el sistema operativo
        String pgpassFilePath;
        if (os.contains("win")) {
            // En Windows, el archivo .pgpass debe estar en %APPDATA%\postgresql\pgpass.conf
            String appData = System.getenv("APPDATA");
            pgpassFilePath = appData + "\\postgresql\\pgpass.conf";
        } else {
            // En Linux/Mac, el archivo .pgpass está en ~/.pgpass
            String homeDir = System.getProperty("user.home");
            pgpassFilePath = homeDir + "/.pgpass";
        }

        // Verificar si el archivo .pgpass ya existe y eliminarlo
        File pgpassFile = new File(pgpassFilePath);
        if (pgpassFile.exists()) {
            // Eliminar el archivo anterior si existe
            boolean deleted = pgpassFile.delete();
            if (deleted) {
                System.out.println("Archivo .pgpass anterior eliminado.");
            } else {
                System.out.println("No se pudo eliminar el archivo .pgpass anterior.");
            }
        }

        // Crear el directorio si no existe
        Path filePath = Paths.get(pgpassFilePath);
        Files.createDirectories(filePath.getParent());

        // Crear el contenido del archivo .pgpass
        String pgpassContent = String.format(
            "%s:%s:*:%s:%s", 
            databaseHost, 
            databasePort, 
            databaseAdminUsername, 
            databaseAdminUserPassword
        );

        // Escribir el contenido en el archivo .pgpass
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(pgpassContent);
        }

        // Establecer los permisos correctos para el archivo
        if (!os.contains("win")) {
            // En sistemas Unix-like, debemos asegurarnos de que el archivo sea solo accesible para el propietario
            pgpassFile.setReadable(true, false);
            pgpassFile.setWritable(true, false);
            pgpassFile.setExecutable(false, false);
        }

        System.out.println("Archivo .pgpass creado");
    }
}
