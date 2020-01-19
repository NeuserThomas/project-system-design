from maven:3.6-jdk-8-alpine AS builder
WORKDIR /app
copy pom.xml .
RUN mvn -e -B dependency:resolve
copy src ./src
RUN mvn -e -B package -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/publicity_service-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "/publicity_service-0.0.1-SNAPSHOT.jar"]
