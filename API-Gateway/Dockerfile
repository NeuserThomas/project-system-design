from maven:3.6-jdk-8-alpine AS builder
MAINTAINER Robin Goussey
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/api-gateway-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "/app/api-gateway-0.0.1-SNAPSHOT.jar"]