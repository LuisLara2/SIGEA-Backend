# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Creamos el .env en recursos para el classpath
RUN touch src/main/resources/.env
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (CAMBIO AQUÍ: Usamos versión completa, no Alpine)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Creamos el .env en la raíz
RUN touch .env

# Instalamos utilidades básicas (opcional pero recomendado para debug)
RUN apt-get update && apt-get install -y curl

COPY --from=build /app/target/*.jar app.jar

# Puerto estándar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]