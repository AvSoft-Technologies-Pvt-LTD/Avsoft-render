# ----------------------
# Build Stage
# ----------------------
FROM maven:3.9.9-eclipse-temurin-24 AS build

WORKDIR /app

# Copy all project files to ensure dependencies and configurations are available
COPY . .

# Download dependencies (optional, for caching)
RUN mvn dependency:go-offline

# Build the project without running tests
RUN mvn clean package -DskipTests

# ----------------------
# Run Stage
# ----------------------
FROM eclipse-temurin:24-jre

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
