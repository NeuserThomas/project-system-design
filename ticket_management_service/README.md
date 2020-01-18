
## **Ticket service (Thomas)**

This service manages everything related to cinema tickets. 

## ***General info***

Server port: 2300

Dependencies:
- Zookeeper
- Kafka
- MySQL

## Deployment

To locally run this service. You need to run:
- Kafka
- Zookeeper

- MySQL server (if there is not already one running) , see command
```
 docker run -d -e MYSQL_DATABASE=Ticket -e MYSQL_ROOT_PASSWORD=ThePassword -p 3306:3306 --name mysqldb mysql:5.7

 ```

### ***Adapters***

Added one REST controller: TicketRestController. The endpoints are:
```
- ticket/ticket/ -> getAllTickets : returns all tickets currently in the database.
- ticket/ticket/screenings/{date} -> getScreeningsByDate() : returns all screenings for the cinema on that day
- ticket/ticket/buyTicket?screeningId={screeningId} -> sellTicket() : buy a ticket for a given screeningId
- ticket/ticket/{id} -> getTicket() : returns the ticket for the given id
- ticket/ticket/screenings -> getScreenings() : returns all screenings in database for the cinema
- ticket/ticket/validateParkingTicket/{ticketId} -> validateParkingTicket() : validates a parkingTicket by using the cinemaTicket provided by its id
```

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
