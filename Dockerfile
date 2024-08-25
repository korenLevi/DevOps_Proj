# Use the official maven/Java image to create a build artifact.
# https://hub.docker.com/r/library/maven
#FROM maven:3.8.5-openjdk-21 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

FROM openjdk:21-slim
VOLUME /tmp
COPY target/final-devops-project-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#mvn io.takari:maven:wrapper

