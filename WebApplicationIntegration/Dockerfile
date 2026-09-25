# Use Java 21 as the runtime environment
FROM eclipse-temurin:21-jre-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR produced by the existing Maven/Jenkins build
COPY target/handling-form-submission-0.0.1-SNAPSHOT.jar app.jar

# Spring Boot runs on port 8080 inside the container
EXPOSE 8080

# Start the packaged application
ENTRYPOINT ["java", "-jar", "app.jar"]