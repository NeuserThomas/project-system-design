FROM openjdk:8
RUN apt-get update && apt-get install -y curl
COPY ./target/publicity_service-0.0.1-SNAPSHOT.jar /usr/src/publicity_service/publicity_service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/usr/src/publicity_service/publicity_service-0.0.1-SNAPSHOT.jar"]
