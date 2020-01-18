# project-system-design

## System design project: Bioscoop system

This repository contains all the folders/ files related to the Bioscoop system project for the course System Design 2019-2020.
The link to the github repo is: [system-design](https://github.com/NeuserThomas/project-system-design)

## Deploying locally (spring tool suite)
When deploying locally, please change the environment. Change the application.properties file to:
```
spring.profiles.active=dev
```
When deploying on kubernetes, leave it on:
```
spring.profiles.active=prod
```

## **Ticket service (Thomas)**

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

- PaymentService


-----------------------------

## **Parking Service (Thomas)**

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


-----------------------------


## **Hall Planning service (Robin)**
### **General info**
- Server port: `2223`
- Dependencies:
  - Zookeeper
  - Kafka
  - MySQL
  - mongo

link to full file: [hallPlanningService](hall_planning_service/readme.md)

-----------------------------


## **Shop service (Robin)**
### **General info**
- Server port: `2230`
- Dependencies:
  - MySQL

link to full file: [shopService](shop_service/readme.md)
-----------------------------

## Databases
### **MySQL**
#### Running locally
To run an appliction locally (in spring tool suite), you might need to set up a local database. We use one server, with multiple databases.
To deploy kafka, you will need to install it, and run the zookeeper, and kafka.
To run our mongo or mysql database, go to the [mysql directory](/mysql) :
```bash
./build_mysqlcontainer.sh
```
This will build the mysql container if not already done, and run it. (port 3306). The reason we use a custom image, is to build our databases.
If we have time, we can try to dynamically create the needed databases.
#### Building docker
To build the database, use the command as written below in the mysql directory:
```
docker build -t mysqldb .
```

### **Mongo**

#### Running locally
To run the mongo db, go to the [bash_scripts directory](/bash_scripts) :
```bash
./build_mongocontainer.sh
```
This will run a standard mongo image, and run it as well.
#### Kubernetes:
At the moment there is an error so our users don't get created. Use the following command:
```
kubectl exec -it <movie-pod-name> mongo
...
db.createUser({ user: "root", pwd: "ThePassword", roles: [ { role : "dbAdmin", db:"movie"}] })
```

### Errors with the databases:
If for some reason the data gets erases in the mongo databases (the movies), then please also erase all data in the mysql.
Since right now we use the mongo id as string in the mysql database. So if the movies get new ids, the mapping is wrong, and it can cause errors for other applications. 
They won't crash, just return empty lists, as the ids are no longer in the database.
So just doing:
```
docker stop mysqldb moviedb
docker rm mysqldb moviedb
```
And rebuilding both you should be fine (you don't need to manually remove the data).
-----------------------------

## Common errors:
##### Bash: `build_docker.sh` & `run_mysqlcontainer.sh`
###### Permission denied errors 
Make sure to grant execution rights:

```bash
# current directory: project-system-design/bash_scripts
chmod +x build_docker.sh
chmod +x ../hall_planning_service/mvnw

chmod +x ./run_mysqlcontainer.sh
```

- - - -
## **frontend**
To run the frontend, go to the directory /frontend in the terminal, and run:
```bash
$ ng serve --open
```
You can also build the frontend with docker, and use it as an image. Do note, that default, it builds by copying the output of the npm build to the docker file, instead of building it in the container (dockerfile_v2, this because of errors).
So you have to run (in a terminal, in the frontend directory):
```
npm run build
```
Do note: this needs node and angular installed.
And then you can build build with docker.
### Testing locally:
Right now the frontend routes all traffic, to the url it came from, on the same port, so you need the ingress controller to run. Or you have to manually change all urls in the service classes. 
### Running in kubernetes:
Make sure the ingress controller and ingress.yaml is depoyed.


- - - -
## **Deployment on kubernetes! (and docker)**
[Kubernets](kubernetes/readme.md)

