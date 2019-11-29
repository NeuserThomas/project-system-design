from openjdk:8

WORKDIR /app/
COPY . /app/


#COPY mvnw pom.xml ./app/
#COPY .mvn ./app/mvn
#COPY src /app/src

RUN apt-get update && apt-get install -y curl --no-install-recommends

#build application
RUN ./mvnw package -DskipTests

ENTRYPOINT ["java","-jar","/app/target/hall_planning_service-0.0.1-SNAPSHOT.jar"]


#PORT That is exposed:
EXPOSE 2223

#CMD ["java", "-jar", "app/target/hall_planning_service-0.0.1-SNAPSHOT.jar"]
