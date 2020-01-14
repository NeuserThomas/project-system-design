FROM openjdk:8

RUN  apt-get update && apt-get install curl

COPY ./target/parking_service-0.0.1-SNAPSHOT.jar /usr/src/parking_service/parking_service-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/usr/src/parking_service/parking_service-0.0.1-SNAPSHOT.jar"]
