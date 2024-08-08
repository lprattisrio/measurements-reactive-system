# Build
FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package

# Execution
FROM openjdk:21
COPY --from=build /app/target/central-service-0.1.0.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","/app/app.jar"]
