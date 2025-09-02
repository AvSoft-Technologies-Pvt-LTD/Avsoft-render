# ----------------------
# Build Stage (with Maven)
# ----------------------
FROM maven:3.9.9-eclipse-temurin-24 AS build

WORKDIR /app

  # Copy pom.xml first (for dependency caching)
COPY pom.xml .

  # Download dependencies (so we don’t re-download every build)
RUN mvn dependency:go-offline

  # Copy the source code (including resources)
COPY src ./src

  # Build the application (resources included automatically)
RUN mvn clean package -DskipTests

  # ----------------------
  # Run Stage
  # ----------------------
FROM eclipse-temurin:24-jre

WORKDIR /app

  # Copy the packaged JAR from build stage
COPY --from=build /app/target/*.jar app.jar

  # Expose port 8080
EXPOSE 8080

  # Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
