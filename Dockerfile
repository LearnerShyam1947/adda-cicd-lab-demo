
# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21-alpine as build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=BUILD /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
