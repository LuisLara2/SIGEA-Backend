package com.zentry.sigea.database;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Acceder a las variables de entorno
        String databaseName = dotenv.get("DB_NAME");
        String databaseUsername = dotenv.get("DB_USERNAME");
        String databasePassword = dotenv.get("DB_PASSWORD");
        String databaseAdminUsername = dotenv.get("DB_ADMIN_USERNAME");
        String databaseAdminUserPassword = dotenv.get("DB_ADMIN_USER_PASSWORD");
        String databaseHost = dotenv.get("DB_HOST");
        String databasePort = dotenv.get("DB_PORT");

        // Imprimir las variables para ver que fueron cargadas
        System.out.println("Si las variables de creacion de base de datos se cargaron correctamente los podra ver debajo:");
        System.out.println("DB_NAME: " + databaseName);
        System.out.println("DB_USER: " + databaseUsername);
    }
}
