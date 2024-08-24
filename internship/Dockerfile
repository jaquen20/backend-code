# # First stage: build
# FROM maven:3.8.5-openjdk-17 AS build

# # Set working directory
# WORKDIR /app

# # Copy the Maven wrapper and pom.xml
# COPY src /app/src
# COPY mvnw pom.xml ./

# # Make the Maven wrapper executable
# RUN chmod +x mvnw

# # Build the application
# RUN ./mvnw clean package -DskipTests

# # Second stage: runtime
# FROM openjdk:17-jdk-alpine

# # Set working directory
# WORKDIR /app

# # Copy the built JAR file from the build stage
# COPY --from=build /app/target/backend-docker.jar backend-docker.jar

# # Expose port 8080
# EXPOSE 8080

# # Run the JAR file
# ENTRYPOINT ["java", "-jar", "/app/backend-docker.jar"]
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY src /app/src
COPY pom.xml ./

# Build the application using Maven directly
RUN mvn clean package -DskipTests
