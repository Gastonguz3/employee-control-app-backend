# ETAPA 1: Compilacion
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# Copio el pom.xml para cargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline
# Copio el codigo fuente
COPY src ./src
# Compilo y genero el JAR
RUN mvn clean package -DskipTests

# ETAPA 2: Imagen
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copio el JAR de la etapa anterior
COPY --from=builder /app/target/*.jar app.jar
# Puerto que usara la app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
