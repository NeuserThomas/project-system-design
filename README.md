# project-system-design
## System design project: Bioscoop system

This repository contains all the folders/ files related to the Bioscoop system project for the course System Design 2019-2020.


## **Ticket service**

### ***Adapters***

Added one REST controller: TicketRestController. The endpoints are:

- ticket/ticket/ -> getAllTickets : returns all tickets currently in the database.
- ticket/ticket/screenings/{date} -> getScreeningsByDate() : returns all screenings for the cinema on that day
- ticket/ticket/buyTicket?screeningId={screeningId} -> sellTicket() : buy a ticket for a given screeningId
- ticket/ticket/{id} -> getTicket() : returns the ticket for the given id
- ticket/ticket/screenings -> getScreenings() : returns all screenings in database for the cinema
- ticket/ticket/validateParkingTicket/{ticketId} -> validateParkingTicket() : validates a parkingTicket by using the cinemaTicket provided by its id

Kafka channels/ commandhandler are implemented, see package adapters.messaging, containing:
- Channels
- PlanningCommandHandler

Payment adapters are implemented to mock the payment functionality, see package adapters.payment, containing:

- IPaymentAdapter
- PaymentAdapter

### ***Domain***

Classes are:
- Ticket
- CinemaProxy
- Hall
- Screening
- ScreeningProxy

### ***Persistence***

Two repositories are used, both interacting with MYSQL databases:
- ScreeningRepository
- TicketRepository

### ***Services***

One service is added, for mocking the functionality of paying,

-PaymentService


-----------------------------



## **Parking Service**

This service is used for parking management. When initialized, one parking is available with 200 free spots.

### ***Adapters***

One REST controller is added: ParkingRestController. The endpoints are:

- /parking/parking -> getParkings() : returns (all of) the parking(s). At the moment, only one parking is used.
- /parking/parking/numberOfFreeSpots -> getNumberOfFreeSpots(): returns how many spots are still free on the parking
- /parking/parking/tickets -> getParkingTickets() : returns all the parkingtickets in the database
- /parking/parking/exitParking/{parkingTicketId} -> exitParking() : for exiting the parking when a valid parkingTicketId is supplied. 
- /parking/parking/validateTicket/{parkingTicketId}?ticketId={cinemaTicketId} -> validateParkingTicket() : for validating your parking ticket when an valid cinemaTicketId is supplied.
- /parking/parking/getTicket -> getParkingTicket() : when entering the parking, a new parkingticket is returned.


### ***Domain***

Classes are: 

- Parking 
- ParkingTicket


### ***Persistence***

Two repositories are used (both interacting with MYSQL databases):

- ParkingRepository
- ParkingTicketRepository


------------------------------



## **Hall Planning service**
### **General info**
- Server port: `2223`
- Dependencies:
  - Zookeeper
  - Kafka
  - MySQL
- mongo

link to full file: [hallPlanningService](hall_planning_service/readme.md)

##### Bash: `build_docker.sh` & `run_mysqlcontainer.sh`
###### Permission denied errors 
Make sure to grant execution rights:

```bash
# current directory: project-system-design/bash_scripts
chmod +x build_docker.sh
chmod +x ../hall_planning_service/mvnw

chmod +x ./run_mysqlcontainer.sh
```
## **frontend**
To run the frontend, go to the direcotry /frontent/frontend in the terminal, and run:
```bash
$ ng serve --open
```
## **Deployment op kubernetes!**
Hieronder het stappenplan om uw applicatie van development (spring tool suite), naar kubernetes om te zetten.
### application.properties
Iedere spring boot app, heeft resources, daarin staat standaard een application.properties file. De andere zijn zelf aangemaakt.(Zie hieronder).
```
application-dev.properties
application-prod.properties
application.properties
```
In de application.properties, zet je dan afhankelijk van als je test, of je in productie draait, prod of dev.
```
spring.profiles.active=prod # of dev
```
De productie ziet er dan zo uit:
```
server.port=2230
spring.jpa.hibernate.ddl-auto=update
#Docker compose:
spring.datasource.url=jdbc:mysql://localhost:3306/Shop
spring.datasource.username=root
spring.datasource.password=ThePassword
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

server.servlet.context-path=/shop
```
En in productie wordt localhost dan vervangen door de docker naam (indien docker compose), of de kubernetes naam (in kubernetes). Dus neem je best dezelfde naam voor beiden.
```
server.port=2230
spring.jpa.hibernate.ddl-auto=update
#Docker compose:
spring.datasource.url=jdbc:mysql://mysqldb:3306/Shop
spring.datasource.username=root
spring.datasource.password=ThePassword
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
server.servlet.context-path=/shop # dit zorgt ervoor dat localhost:2230/shop de applicatie op draait
```
Je zorgt er best voor dat je een server.servlet.context-path defineert, zo worden alle calls van die applicatie minstens onderscheiden door dit. (En heeft het maar 1 ingress route nodig).

### Docker file
Iedere applicatie heeft een dockerfile nodig, om het tot een image om te vormen, hieronder een voorbeeld:
```
from maven:3.6-jdk-8-alpine AS builder
#from openjdk:8
WORKDIR /app
copy pom.xml .
RUN mvn -e -B dependency:resolve
copy src ./src
RUN mvn -e -B package -DskipTests # maakt de jar

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/shop_service-0.0.1-SNAPSHOT.jar /
CMD ["java", "-jar", "/shop_service-0.0.1-SNAPSHOT.jar","-Dspring.profiles.active=prod"] #hoe gestart?
```
Dan doe je in de directory waar dze zich bevindt:
```
docker build -t <naam> .
```
### Exporteren naar kubernetes
#### lokale images
Als je werkt met microk8s, dan weet hij niet waar de lokale docker images zijn. Dus daarom kan je het script:
```
./save_image.sh <image_name>
``` 
gebruiken, om de image in het microk8s image register te registreren. 
#### images registren op docker hub.
Je kan ook je image pushen naar een docker hub registry. Ik heb er al enkele, bv rgoussey/frontend, rgousey/shop etc. Via:
```
docker tag frontend rgoussey/frontend
docker push frontend
```
Kan je ze zo pushen. Let op je moet wel ingelogd zijn (docker login), en toegang hebben tot de registry (ik moet dat goedkeuren voor die registries.
Je kan ook zelf registries aanmaken. Later kan je dan zo aan rgoussey/frontend de image pullen. (in kubernetes)
### Werking kubernetes.
Kubernetes zal werken als je container orchestration tool. Het eerste wat je moet doen is een deployment creeeren:
```
apiVersion: apps/v1 # definitie
kind: Deployment # Een deployment: het kijkt automatisch om voor u een aantal pods te creeeren, afhankelijk van replica's
metadata:
  labels:
    app: shop ## belangrijk, dit wordt gebruikt voor services om te selecteren welke pods het gebruikt. Indien er bij de labels en selectors iets niet klopt, dan heeft je service geen endpoint
  name: shop
spec:
  replicas: 1 # aantal pods/replicas
  selector:
    matchLabels:
      app: shop ## zorg dat dit klopt
  template:
    metadata:
      labels:
        app: shop
    spec:
      containers:
      - image: shop # naam van de image
        imagePullPolicy: Never # dit enkel wanneer het lokaal staat, indien je hier rgoussey/shop, zet dan moet dit weg
        name: shop
        ports:
        - containerPort: 2230 # op welke poort draait je container
```
Daarna heb je een service nodig, die in de pod gaat draaien:
```
apiVersion: v1
kind: Service
metadata:
  labels:
    app: shop # zorg dat dit correct is
  name: shop
spec:
  type: ClusterIP # type van kubernetes, nekel beschikbaar in cluster
  ports:
  - name: "2230" # gewoon de naam
    port: 2230 # Poort in de cluster
    targetPort: 2230 # poort van de container
  selector:
    app: shop # weer zorg dat dit correct is.
```
Je kan deze ofwel in aparte files steken (.yaml), of in een gescheiden door ---. Vervolgens dien je 
```
(microk8s.) kubectl apply -f <file>
```
te doen om ze te deployen. Via kubectl get of describe krijg je meer info over het deployement of de services.
#### Ingress
Vervolgens moet je vanaf de buitenwereld aan deze service kunnen, dit doe je door in een ingress, een regel toe te passen, die al het verkeer doorgeeft.
```
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: gateway-ingress
spec:
  rules:
  - http:
      paths:
      - path: /shop #indien er /shop staat, dan wordt alle aanvragen naar deze service omgeleid.
        backend:
          serviceName: shop
          servicePort: 2230
      - path: / #geen url naar frontend
        backend:
          serviceName: frontend
          servicePort: 80
```
Daarom is het belangrijk dat je applicatie mogelijks via een algemene url /shop omleidbaar is. 
Indien je er meerdere hebt (bv per restcontroller, zonder /shop ervoor), zou je hier per url moeten omleiden.
#### Ingress controller:
Uitleg over ingress controller:
https://www.youtube.com/watch?v=VicH6KojwCI&fbclid=IwAR3MchJe-CeINbiEIdcq8acLMNJEXv2AtmCR0_RcDYH-GlWQ742Kf3jaF_A
In microk8s kan je via :
```
microk8s.enable ingress
```
een ingebouwde ingress controller gebruiken. Ik ben zelf nog aan het uitzoeken hoe ik deze goed deploy. Enige hulp is altijd welkom.
#### Eigen ingress controller:
Er zijn nu 2 yamls toegevoegd, die zelf een deployen. Zie kubernetes/definitive_versions/mandator.yaml en de kubernetes/definitive_versions/service-nodeport.yaml. Indien je deze deployt, heb je de microk8s ingress niet nodig.

Dit was mijn TED talk, bedankt voor uw aandacht.
