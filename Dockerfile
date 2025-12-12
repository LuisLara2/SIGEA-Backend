# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Truco: Crear .env vacío para evitar errores de compilación
RUN touch src/main/resources/.env
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Imagen Ligera Alpine)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Truco: Crear .env vacío para evitar errores de ejecución
RUN touch .env
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]