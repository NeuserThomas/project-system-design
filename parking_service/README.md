## **Parking Service (Thomas)**

This service is used for parking management. When initialized, one parking is available with 200 free spots.

## ***General info***

Server port: 2301

Dependencies:
- MySQL

## ***Deployment***
To run locally, first you need:

- [ mysql server](https://github.com/NeuserThomas/project-system-design#running-locally)
 

### ***Adapters***

One REST controller is added: ParkingRestController. The endpoints are:

```

- /parking/parking (GET) -> getParkings() : returns (all of) the parking(s). At the moment, only one parking is used.
- /parking/parking/numberOfFreeSpots (GET) -> getNumberOfFreeSpots(): returns how many spots are still free on the parking
- /parking/parking/tickets (GET) -> getParkingTickets() : returns all the parkingtickets in the database
- /parking/parking/exitParking/{parkingTicketId} (DELETE) -> exitParking() : for exiting the parking when a valid parkingTicketId is supplied. 
- /parking/parking/validateTicket/{parkingTicketId}?ticketId={cinemaTicketId} (PUT) -> validateParkingTicket() : for validating your parking ticket when an valid cinemaTicketId is supplied.
- /parking/parking/getTicket (GET) -> getParkingTicket() : when entering the parking, a new parkingticket is returned.

```

### ***Domain***

Classes are: 

- Parking 
- ParkingTicket

### ***Persistence***

Two repositories are used, both interacting with MYSQL databases:
- ParkingRepository
- ParkingTicketRepository
