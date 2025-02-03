# Use an official OpenJDK base image with Java 21
FROM openjdk:21-jdk-slim as build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# Create a minimal runtime image with just the built JAR
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Command to run the Spring Boot app (no need to hardcode env vars here)
ENTRYPOINT ["java", "-jar", "app.jar"]
