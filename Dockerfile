# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Truco: Crear .env en recursos
RUN touch src/main/resources/.env
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (CAMBIO IMPORTANTE: Usamos la versión completa, NO Alpine)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Truco: Crear .env en raíz
RUN touch .env
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]