# FROM openjdk:17-jdk-alpine
# WORKDIR /app
# ADD target/backend-docker.jar backend-docker.jar
# COPY . .
# RUN ./mvnw clean package -DskipTests
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "/backend-docker.jar"]

# FROM openjdk:17-jdk-alpine
# WORKDIR /app



# Set working directory
WORKDIR /app

# Clone the GitHub repository
RUN git clone https://github.com/jaquen20/Course-selection-project.git .

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a minimal Java runtime image for the final image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/backend-docker.jar backend-docker.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/backend-docker.jar"]


