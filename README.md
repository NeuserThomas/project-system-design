# project-system-design
## System design project: Bioscoop system

This repository contains all the folders/ files related to the Bioscoop system project for the course System Design 2019-2020.


## **TicketManagement service**

### ***Domain***

Added next classes:

- Ticket
- Movie
- MovieSchedule (MovieSchedule is an array of Movies, used when getting the MovieSchedule from the MoviePlanner service)
- TicketStatus (enum containing the states that a Ticket can have)

### ***Persistence***

Added a TicketRepository (currrently working with H2 database) for storing the tickets

### ***Adapters***

Added a RestController (that can later be used for validateTicket)

## **Planning service**
### **General info**
Server port: 2223
Uses Zookeeper, Kafka, and MySQL
### **Running the application**
#### From IDE:
Make sure Zookeeper,Kafka and MySQL (container) is running, or change to H2
#### Docker:
There are 2 dockerfiles, one to build, and one to run

There also is a script that builds with the Dockerfile\_build. One just uses the jar, and the other one builds from scratch

Make sure that for docker compose these are enabled in settings properties:
spring.cloud.stream.kafka.binder.brokers=kafka
spring.cloud.stream.kafka.binder.zkNodes=zookeeper

### **Rest Calls (WIP)**
Available rest calls:
- /cinema						: Returns all Cinemas
- /cinema/{cinemaId}			: Returns cinema with id
- /hall/cinema/{cinemaId}	: Returns all halls from a certain cinema
- /halls/{cinemaId}/{hallId}	: Return the hall with hallId
- /planning						: Return all days
- /planning/{date}				: Return all days after date
- /planning/cinema/{cinemaId}		: Return all planned days from today for cinema
- /planning/{cinemaId}/{day}	: Return planning for certain day (WIP)
