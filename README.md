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
