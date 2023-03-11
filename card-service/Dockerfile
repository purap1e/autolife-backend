FROM maven:3.8.4-openjdk-17 AS builder
ENV TZ="Asia/Almaty" JAVA_FLAGS="-Xmx500m"
ARG directory
WORKDIR /app
COPY pom.xml .
COPY src ./src/
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=./card-service/target/*.jar
WORKDIR /app
COPY --from=builder /app/${directory}/target/*.jar ./card-service.jar
EXPOSE 8001
CMD ["java", "-jar", "./card-service.jar"]
