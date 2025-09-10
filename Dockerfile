# Use an official OpenJDK image as a base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml
# This ensures the build environment is properly set up.
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Grant execute permissions to the Maven wrapper script
# This is crucial for the script to run successfully.
RUN chmod +x ./mvnw

# Copy the source code
COPY src ./src

# Build the application using the Maven wrapper
RUN ./mvnw clean package -DskipTests

# Copy the generated JAR file to a consistent name
# Using a wildcard handles version number changes automatically.
RUN cp target/*.jar app.jar

# Expose the port your application listens on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]