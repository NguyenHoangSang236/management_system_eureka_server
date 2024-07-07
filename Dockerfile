FROM ubuntu:latest
FROM openjdk:19-jdk-alpine

COPY keystore.p12 app/keystore.p12
COPY target/eureka_server-0.0.1-SNAPSHOT.jar app/target/eureka_server-0.0.1-SNAPSHOT.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "target/eureka_server-0.0.1-SNAPSHOT.jar"]

EXPOSE 8761