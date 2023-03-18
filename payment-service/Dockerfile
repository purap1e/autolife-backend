FROM maven:3.8.4-openjdk-17 AS builder
ENV TZ="Asia/Almaty" JAVA_FLAGS="-Xmx500m"
ARG directory
WORKDIR /app
COPY pom.xml .
COPY src ./src/
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=./payment-server/target/*.jar
WORKDIR /app
COPY --from=builder /app/${directory}/target/*.jar ./payment-server.jar
EXPOSE 8777
CMD ["java", "-jar", "./payment-server.jar"]

