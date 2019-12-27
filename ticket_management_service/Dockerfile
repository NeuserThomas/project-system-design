FROM openjdk:8

RUN  apt-get update && apt-get install curl

COPY ./target/ticket_management_service-0.0.1-SNAPSHOT.jar /usr/src/ticket_management/ticket_management_service-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/usr/src/ticket_management/ticket_management_service-0.0.1-SNAPSHOT.jar"]
