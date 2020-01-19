from maven:3.6-jdk-8-alpine AS builder
#from openjdk:8
WORKDIR /app
copy pom.xml .
RUN mvn -e -B dependency:resolve
copy src ./src
RUN mvn -e -B package -DskipTests
#CMD ["java", "-jar","/app/shop_service-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/shop_service-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "/shop_service-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=dev"]
