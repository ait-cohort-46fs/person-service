FROM eclipse-temurin:17-jre-alpine

LABEL authors="edward"

WORKDIR /app

COPY ./target/person-service-0.0.1-SNAPSHOT.jar ./person-service.jar

ENTRYPOINT ["java", "-jar", "/app/person-service.jar"]